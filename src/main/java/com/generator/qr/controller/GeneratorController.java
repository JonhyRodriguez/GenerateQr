package com.generator.qr.controller;

import com.generator.qr.dto.GeneratorRequest;
import com.generator.qr.dto.QRCodeResponse;
import com.generator.qr.service.LinkManagerService;
import com.generator.qr.service.LinkStorageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GeneratorController {

    private final LinkManagerService linkManagerService;
    private final LinkStorageService linkStorageService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateQRCode(@Valid @RequestBody GeneratorRequest request) {
        try {
            String id = linkManagerService.createShortLink(request.getLink());
            String shortUrl = "https://generateqr-0tnr.onrender.com/redirect/" + id;
            return ResponseEntity.ok(new QRCodeResponse("QR generated successfully", shortUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new QRCodeResponse("Error generating QR code: " + e.getMessage(), null));
        }
    }

    @GetMapping("/redirect/{id}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String id) {
        Optional<String> originalUrl = linkStorageService.getOriginalUrl(id);
        if (originalUrl.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(originalUrl.get()));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
