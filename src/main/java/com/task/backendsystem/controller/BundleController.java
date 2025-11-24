package com.task.backendsystem.controller;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;
import com.task.backendsystem.service.BundleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bundles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BundleController {

    private final BundleService bundleService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BundleResponse>> getBundle(@PathVariable Long id) {
        return bundleService.getBundleById(id).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createBundle(@Valid @RequestBody BundleRequest request) {
        return bundleService.createBundle(request)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body("Bundle created successfully")));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<String>> updateBundle(@PathVariable Long id, @Valid @RequestBody BundleRequest request) {
        return bundleService.updateBundle(id, request)
                .then(Mono.just(ResponseEntity.ok("Bundle updated successfully")));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteBundle(@PathVariable Long id) {
        return bundleService.deleteBundle(id)
                .then(Mono.just(ResponseEntity.ok("Bundle deleted successfully")));
    }
}