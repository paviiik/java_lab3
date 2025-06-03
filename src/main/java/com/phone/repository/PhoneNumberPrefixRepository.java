package com.phone.repository;

import com.phone.model.PhoneNumberPrefix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhoneNumberPrefixRepository extends JpaRepository<PhoneNumberPrefix, Long> {

    List<PhoneNumberPrefix> findByCountryCode(String countryCode);

    @Query("SELECT p FROM PhoneNumberPrefix p WHERE p.country.name = :name")
    List<PhoneNumberPrefix> findByCountryName(@Param("name") String name);

    boolean existsByPrefixAndCountryCode(String prefix, String countryCode);
}
