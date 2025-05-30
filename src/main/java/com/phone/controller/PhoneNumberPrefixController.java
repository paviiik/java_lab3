package com.phone.controller;

import com.phone.dto.PrefixDtoRequest;
import com.phone.dto.PrefixDtoResponse;
import com.phone.service.PhoneNumberPrefixService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prefixes")
public class PhoneNumberPrefixController {

    private final PhoneNumberPrefixService service;

    public PhoneNumberPrefixController(PhoneNumberPrefixService service) {
        this.service = service;
    }

    @GetMapping("/by-country-name")
    public List<PrefixDtoResponse> getByCountryName(@RequestParam String name) {
        return service.getByCountryName(name);
    }

    @GetMapping
    public List<PrefixDtoResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/country/{code}")
    public List<PrefixDtoResponse> getByCountry(@PathVariable String code) {
        return service.getByCountry(code);
    }

    @GetMapping("/{id}")
    public PrefixDtoResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/save")
    public PrefixDtoResponse save(@RequestBody PrefixDtoRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public PrefixDtoResponse update(@PathVariable Long id, @RequestBody PrefixDtoRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
