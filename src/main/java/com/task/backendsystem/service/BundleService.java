package com.task.backendsystem.service;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;

public interface BundleService {
    BundleResponse getBundle(Long id);

    void createBundle(BundleRequest request);

    void updateBundle(Long id, BundleRequest request);

    void deleteBundle(Long id);
}