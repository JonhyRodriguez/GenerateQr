package com.generator.qr.service.impl;

import com.generator.qr.service.LinkManagerService;
import com.generator.qr.service.LinkStorageService;
import com.generator.qr.service.QRGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class LinkManagerServiceImpl implements LinkManagerService {

    private final QRGeneratorService qrGeneratorService;
    private final LinkStorageService linkStorageService;

    @Override
    public String createShortLink(String originalUrl) throws Exception {
        String id = UUID.randomUUID().toString().substring(0, 10);
        boolean saved = linkStorageService.saveLink(id, originalUrl);
        if (!saved) throw new RuntimeException("No se pudo guardar el enlace");

        String shortUrl = "https://tusitio.onrender.com/redirect/" + id;
        qrGeneratorService.generateQRCodeImage(shortUrl, id, 300, 300);
        return id;
    }

}

