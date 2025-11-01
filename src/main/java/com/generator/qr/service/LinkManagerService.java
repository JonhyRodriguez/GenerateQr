package com.generator.qr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LinkManagerService {

    private final QRGeneratorService qrGeneratorService;
    private final LinkStorageService linkStorageService;


    public String createShortLink(String originalUrl) throws Exception {
        String id = UUID.randomUUID().toString().substring(0, 10);
        boolean saved = linkStorageService.saveLink(id, originalUrl);
        if (!saved) throw new RuntimeException("Failed to save the link");

        String shortUrl = "https://generateqr-0tnr.onrender.com/redirect/" + id;
        qrGeneratorService.generateQRCodeImage(shortUrl, id, 300, 300);
        return id;
    }

}

