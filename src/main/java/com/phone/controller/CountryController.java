package com.phone.controller;

import com.phone.dto.CountryDtoRequest;
import com.phone.dto.CountryDtoResponse;
import com.phone.service.CountryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CountryDtoResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/{code}")
    public CountryDtoResponse update(@PathVariable String code, @RequestBody CountryDtoRequest request) {
        return service.update(code, request);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        service.delete(code);
    }

    @GetMapping("/{code}")
    public CountryDtoResponse getByCode(@PathVariable String code) {
        return service.getByCode(code);
    }

    @PostMapping("/save")
    public CountryDtoResponse save(@RequestBody CountryDtoRequest request) {
        return service.save(request);
    }

    @GetMapping("/lookup")
    public CountryDtoResponse lookup(@RequestParam String value) {
        return service.lookup(value);
    }
}
