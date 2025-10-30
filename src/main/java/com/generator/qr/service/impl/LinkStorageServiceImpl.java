package com.generator.qr.service.impl;

import com.generator.qr.service.LinkStorageService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class LinkStorageServiceImpl implements LinkStorageService {

    private static final String JSON_PATH = System.getProperty("user.dir") + "/config/links.json";

    @Override
    public boolean saveLink(String id, String originalUrl) {
        try {
            JSONObject links = loadLinks();
            links.put(id, originalUrl);
            Files.writeString(Paths.get(JSON_PATH), links.toString(2));
            return true;
        } catch (IOException e) {
            System.err.println("❌ Error al guardar links.json: " + e.getMessage());
            return false;
        }
    }

    @Override
    public JSONObject loadLinks() {
        try {
            String content = Files.readString(Paths.get(JSON_PATH));
            return new JSONObject(content);
        } catch (IOException e) {
            System.err.println("⚠️ No se pudo leer el archivo links.json: " + e.getMessage());
            return new JSONObject();
        }
    }

    @Override
    public Optional<String> getOriginalUrl(String id) {
        JSONObject links = loadLinks();
        return Optional.ofNullable(links.optString(id, null));
    }
}
