package com.phone.dto;

import lombok.Data;
import java.util.List;

@Data
public class CountryDtoResponse {
    private String code;
    private String name;
    private String phoneCode;
    private List<PrefixDtoResponse> prefixes;
}
