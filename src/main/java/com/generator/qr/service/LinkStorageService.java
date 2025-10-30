package com.generator.qr.service;

import org.json.JSONObject;

import java.util.Optional;

public interface LinkStorageService {

    boolean saveLink(String id, String originalUrl);

    JSONObject loadLinks();

    Optional<String> getOriginalUrl(String id);
}
