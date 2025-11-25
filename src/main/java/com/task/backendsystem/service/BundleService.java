package com.task.backendsystem.service;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;

public interface BundleService {
    BundleResponse getBundle(Long id, String correlationId);

    void createBundle(BundleRequest request, String correlationId);

    void updateBundle(Long id, BundleRequest request, String correlationId);

    void deleteBundle(Long id, String correlationId);
}