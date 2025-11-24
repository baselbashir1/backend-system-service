package com.task.backendsystem.service;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;
import reactor.core.publisher.Mono;

public interface BundleService {
    Mono<BundleResponse> getBundleById(Long id);

    Mono<Void> createBundle(BundleRequest request);

    Mono<Void> updateBundle(Long id, BundleRequest request);

    Mono<Void> deleteBundle(Long id);
}