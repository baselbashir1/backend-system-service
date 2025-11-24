package com.task.backendsystem.mapper;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;
import com.task.backendsystem.model.Bundle;
import org.springframework.stereotype.Service;

@Service
public class BundleMapper {

    public BundleResponse mapToBundleResponse(Bundle bundle) {
        return BundleResponse.builder()
                .id(bundle.getId())
                .name(bundle.getName())
                .description(bundle.getDescription())
                .price(bundle.getPrice())
                .createdAt(bundle.getCreatedAt())
                .updatedAt(bundle.getUpdatedAt())
                .build();
    }

    public Bundle mapToBundle(BundleRequest request) {
        Bundle bundle = new Bundle();
        bundle.setName(request.name());
        bundle.setDescription(request.description());
        bundle.setPrice(request.price());
        return bundle;
    }
}