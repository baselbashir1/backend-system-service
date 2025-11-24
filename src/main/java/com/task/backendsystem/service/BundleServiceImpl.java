package com.task.backendsystem.service;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;
import com.task.backendsystem.mapper.BundleMapper;
import com.task.backendsystem.model.Bundle;
import com.task.backendsystem.repository.BundleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BundleServiceImpl implements BundleService {

    private final BundleMapper bundleMapper;
    private final BundleRepository bundleRepository;

    @Override
    public BundleResponse getBundleById(Long id) {
        return bundleMapper.mapToBundleResponse(bundleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bundle not found")));
    }

    @Override
    public void createBundle(BundleRequest request) {
        bundleRepository.save(bundleMapper.mapToBundle(request));
        log.info("Bundle created successfully");
    }

    @Override
    public void updateBundle(Long id, BundleRequest request) {
        Bundle bundle = bundleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bundle not found"));

        if (request.name() != null) {
            bundle.setName(request.name());
        }
        if (request.description() != null) {
            bundle.setDescription(request.description());
        }
        if (request.price() != null) {
            bundle.setPrice(request.price());
        }

        bundleRepository.save(bundle);
        log.info("Bundle updated successfully");
    }

    @Override
    public void deleteBundle(Long id) {
        if (!bundleRepository.existsById(id)) {
            throw new EntityNotFoundException("Bundle not found");
        }
        bundleRepository.deleteById(id);
        log.info("Bundle deleted successfully");
    }
}