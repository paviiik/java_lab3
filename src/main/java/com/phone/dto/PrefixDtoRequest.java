package com.phone.dto;

import lombok.Data;

@Data
public class PrefixDtoRequest {
    private String prefix;
    private String regionName;
    private String countryCode;
}