package com.generator.qr.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class KeepAliveTask {

    @Scheduled(cron = "45 * * * * *")
    public void alive() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generateqr-0tnr.onrender.com/"))
                    .GET()
                    .build();

            client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (Exception e) {
            log.error("❌ Error en el ping: " + e.getMessage());
        }
    }
}
