package com.chatai.demo.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
public class PromptFileReader {

    public String readFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        Path path = Paths.get(resource.getURI());
        return Files.lines(path).collect(Collectors.joining(System.lineSeparator()));
    }
}
