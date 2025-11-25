package com.task.backendsystem.controller;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;
import com.task.backendsystem.service.BundleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bundles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BundleController {

    private final BundleService bundleService;

    @GetMapping("/{id}")
    public ResponseEntity<BundleResponse> getBundle(@PathVariable Long id) {
        return new ResponseEntity<>(bundleService.getBundle(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBundle(@Valid @RequestBody BundleRequest request) {
        bundleService.createBundle(request);
        return new ResponseEntity<>("Bundle created successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBundle(@PathVariable Long id, @Valid @RequestBody BundleRequest request) {
        bundleService.updateBundle(id, request);
        return new ResponseEntity<>("Bundle updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBundle(@PathVariable Long id) {
        bundleService.deleteBundle(id);
        return new ResponseEntity<>("Bundle deleted successfully", HttpStatus.OK);
    }
}