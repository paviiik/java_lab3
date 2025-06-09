package com.phone.cache;

import com.phone.dto.CountryDtoResponse;
import com.phone.dto.PrefixDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class CountryCache extends FifoCache<CountryDtoResponse> {
    public CountryCache() {
        super(10);
    }
}
