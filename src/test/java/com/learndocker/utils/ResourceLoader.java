package com.learndocker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {
    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource(String path){
        log.info("reading resource from path {}", path);
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if(Objects.nonNull(stream))
            return stream;
        try {
            return Files.newInputStream(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
