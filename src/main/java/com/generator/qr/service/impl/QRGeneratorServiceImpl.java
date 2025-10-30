package com.generator.qr.service.impl;

import com.generator.qr.service.QRGeneratorService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class QRGeneratorServiceImpl implements QRGeneratorService {

    @Override
    public String generateQRCodeImage(String content, String fileName, int width, int height) throws Exception {
        BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
        String outputPath = System.getProperty("user.home") + "/Downloads/" + fileName + ".png";
        Path path = Paths.get(outputPath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        return outputPath;
    }
}
