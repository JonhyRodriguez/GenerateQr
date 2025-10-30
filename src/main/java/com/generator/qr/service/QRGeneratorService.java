package com.generator.qr.service;

public interface QRGeneratorService {
    String generateQRCodeImage(String content, String fileName, int width, int height) throws Exception;
}
