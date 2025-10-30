package com.generator.qr.service;

public interface LinkManagerService {
    String createShortLink(String originalUrl) throws Exception;
}
