package org.com.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtils {

    @SneakyThrows
    private static JsonReader getJsonReader(String jsonPath) {
        JsonReader reader;
        reader = new JsonReader(new FileReader(jsonPath));
        return reader;
    }

    @SneakyThrows
    public static <T> T fromFile(File f, Class<T> clazz) {
        Path p = Path.of(f.getAbsolutePath());
        return fromJSON(Files.readString(p), clazz);
    }

    public static <T> T fromJSON(String JSON, Class<T> clazz) {
        return new Gson().fromJson(JSON, clazz);
    }

    public static <T> T to(String jsonPath, Class<?> clazz) {
        JsonReader reader = getJsonReader(jsonPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, clazz);
    }

    public static <T> String toJSON(Object object) {
        return new Gson().toJson(object);
    }

}
