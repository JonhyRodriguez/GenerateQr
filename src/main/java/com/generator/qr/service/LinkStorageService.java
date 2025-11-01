package com.generator.qr.service;

import org.json.JSONObject;

import java.util.Optional;

public interface LinkStorageService {

    boolean saveLink(String id, String originalUrl);

    Optional<String> getOriginalUrl(String id);
}
