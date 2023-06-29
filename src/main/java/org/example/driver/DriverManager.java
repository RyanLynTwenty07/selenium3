package org.example.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.currentThread;

public class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    static final Map<Long, Driver> threadDriver = new ConcurrentHashMap<>(4);
    static final Map<Long, Configuration> threadConfig = new ConcurrentHashMap<>(4);

    public DriverManager() {
        Configuration config = new Configuration(true);
        log.debug("Configuration loaded {}", config.toJson());
        long threadId = currentThread().getId();
        this.threadConfig.put(threadId, config);
    }

    public void setConfig(Configuration config) {
        threadConfig.put(getCurrentThreadId(), config);
    }

    public Configuration getConfig() {
        long threadId = currentThread().getId();
        Configuration config = this.threadConfig.get(threadId);
        if (config == null) {
            throw new IllegalStateException("No configuration is found to current thread: " + threadId + ". You need to call setConfig(config) first.");
        }
        return config;
    }

    public void open(String url) {
        initDriver();
        if(url!=null){
            driver().open(url);
        }
    }

    public void open() {
        open(null);
    }

    public void close() {
        if (threadDriver.size() > 0) {
            long threadId = getCurrentThreadId();
            driver().close();
            threadDriver.remove(threadId);
        }
    }

    private void initDriver() {
        Map<Long, Driver> map = threadDriver;
        long threadId = currentThread().getId();
        if (!map.containsKey(threadId)) {
            Driver driver = new Driver(threadConfig.get(currentThread().getId()));
            threadDriver.put(threadId, driver);
        }
    }

    public static Driver driver() {
        Map<Long, Driver> map = threadDriver;
        long threadId = currentThread().getId();
        if (!map.containsKey(threadId)) {
            throw new IllegalStateException(
                    "No web driver is bound to current thread: " + threadId + ". You need to call open(url) first.");
        }
        return map.get(threadId);
    }


//    private static Driver getCurrentDriver() {
//        return getDriver(getCurrentThreadId());
//    }
//
//    private static Driver getDriver(long threadId) {
//        return threadDriver.get(threadId);
//    }

    private static long getCurrentThreadId() {
        return currentThread().getId();
    }
}
