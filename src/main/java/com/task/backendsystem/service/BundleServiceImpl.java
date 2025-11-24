package com.task.backendsystem.service;

import com.task.backendsystem.dto.request.BundleRequest;
import com.task.backendsystem.dto.response.BundleResponse;
import com.task.backendsystem.mapper.BundleMapper;
import com.task.backendsystem.model.Bundle;
import com.task.backendsystem.repository.BundleRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class BundleServiceImpl implements BundleService {

    private final BundleMapper bundleMapper;
    private final BundleRepository bundleRepository;

    @Override
    public Mono<BundleResponse> getBundleById(Long id) {
        return Mono.fromCallable(() ->
                bundleMapper.mapToBundleResponse(bundleRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Bundle not found"))
                )
        ).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> createBundle(BundleRequest request) {
        return Mono.fromRunnable(() -> {
            Bundle bundle = bundleRepository.findByName(request.name());

            if (bundle != null) {
                throw new EntityExistsException("Bundle with name " + request.name() + " already exists");
            }

            bundleRepository.save(bundleMapper.mapToBundle(request));
            log.info("Bundle created successfully");
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    public Mono<Void> updateBundle(Long id, BundleRequest request) {
        return Mono.fromRunnable(() -> {
            Bundle bundle = bundleRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Bundle not found"));

            if (request.name() != null) bundle.setName(request.name());
            if (request.description() != null) bundle.setDescription(request.description());
            if (request.price() != null) bundle.setPrice(request.price());

            bundleRepository.save(bundle);
            log.info("Bundle updated successfully");
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    public Mono<Void> deleteBundle(Long id) {
        return Mono.fromRunnable(() -> {
            if (!bundleRepository.existsById(id)) {
                throw new EntityNotFoundException("Bundle not found");
            }
            bundleRepository.deleteById(id);
            log.info("Bundle deleted successfully");
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}