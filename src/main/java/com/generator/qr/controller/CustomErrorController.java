package com.generator.qr.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Something went wrong");
        response.put("status", status != null ? status.toString() : "unknown");
        response.put("path", path != null ? path : "unknown");

        return ResponseEntity.status(HttpStatus.valueOf(Integer.parseInt(response.get("status")))).body(response);
    }

}

