package com.phone.dto;

import lombok.Data;

@Data
public class CountryDtoRequest {
    private String code;
    private String name;
    private String phoneCode;
}