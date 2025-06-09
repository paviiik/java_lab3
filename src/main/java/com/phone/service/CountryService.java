package com.phone.service;

import com.phone.cache.CountryCache;
import com.phone.cache.PrefixCache;
import com.phone.dto.CountryDtoRequest;
import com.phone.dto.CountryDtoResponse;
import com.phone.exception.NotFoundException;
import com.phone.mapper.CountryMapper;
import com.phone.model.Country;
import com.phone.model.PhoneNumberPrefix;
import com.phone.repository.CountryRepository;
import com.phone.repository.PhoneNumberPrefixRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CountryService {

    private final CountryRepository repository;
    private final CountryMapper mapper;
    private PhoneNumberPrefixRepository prefixRepository;
    private PrefixCache cache;
    private CountryCache countryCache;

    public List<CountryDtoResponse> getAll() {
        return repository.findAll().stream()
                .map(country -> {
                    CountryDtoResponse dto = mapper.toDto(country);
                    countryCache.put(Long.parseLong(dto.getCode()), dto);
                    return dto;
                })
                .toList();
    }

    public CountryDtoResponse save(CountryDtoRequest request) {
        if (repository.existsById(request.getCode())) {
            throw new IllegalArgumentException("Country with code '" + request.getCode() + "' already exists");
        }
        Country saved = repository.save(mapper.toEntity(request));
        CountryDtoResponse response = mapper.toDto(saved);
        countryCache.put(Long.parseLong(saved.getCode()), response);
        return response;
    }

    public CountryDtoResponse getByCode(String code) {
        Long key = Long.parseLong(code);
        CountryDtoResponse cached = countryCache.get(key);
        if (cached != null) {
            return cached;
        }
        CountryDtoResponse response = repository.findById(code)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Country with code '" + code + "' not found"));
        countryCache.put(key, response);
        return response;
    }

    public CountryDtoResponse update(String code, CountryDtoRequest request) {
        Long key = Long.parseLong(code);
        CountryDtoResponse response = repository.findById(code)
                .map(existing -> {
                    existing.setName(request.getName());
                    existing.setPhoneCode(request.getPhoneCode());
                    return mapper.toDto(repository.save(existing));
                })
                .orElseThrow(() -> new NotFoundException("Country with code '" + code + "' not found"));
        countryCache.put(key, response);
        return response;
    }

    public void delete(String code) {
        Long key = Long.parseLong(code);
        List<PhoneNumberPrefix> prefixes = prefixRepository.findByCountryCode(code);
        repository.deleteById(code);
        countryCache.remove(key);
        for (PhoneNumberPrefix prefix : prefixes) {
            cache.remove(prefix.getId());
        }
    }
}
