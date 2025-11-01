package com.generator.qr.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.qr.service.LinkStorageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LinkStorageServiceImpl implements LinkStorageService {
    @Value("${app.links-file}")
    private String linksFilePath;

    @Override
    public boolean saveLink(String id, String url) {
        try {
            Path path = Paths.get(linksFilePath);
            Files.createDirectories(path.getParent());
            Map<String, String> links = readLinks(path);
            links.put(id, url);
            writeLinks(path, links);
            return true;
        } catch (IOException e) {
            System.err.println("❌ Error al guardar el link: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<String> getOriginalUrl(String id) {
        try {
            Path path = Paths.get(linksFilePath);
            Map<String, String> links = readLinks(path);
            System.out.println("Buscando ID: " + id);
            System.out.println("Contenido del archivo: " + links);
            return Optional.ofNullable(links.get(id));
        } catch (IOException e) {
            System.err.println("❌ Error al leer el archivo: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Map<String, String> readLinks(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (!Files.exists(path)) {
            return new HashMap<>();
        }
        try (InputStream is = Files.newInputStream(path)) {
            return mapper.readValue(is, new TypeReference<Map<String, String>>() {});
        }
    }

    private void writeLinks(Path path, Map<String, String> links) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (OutputStream os = Files.newOutputStream(path)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(os, links);
        }
    }
}
