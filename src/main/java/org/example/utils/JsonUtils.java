package org.example.utils;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtils {

    @SneakyThrows
    public static <T> T fromFile(File f, Class<T> clazz) {
        Path p = Path.of(f.getAbsolutePath());
        return fromJSON(Files.readString(p), clazz);
    }
    public static <T> T fromJSON(String JSON, Class<T> clazz) {
        return new Gson().fromJson(JSON, clazz);
    }

    public static <T> String toJSON(Object object) {
        return new Gson().toJson(object);
    }

}
