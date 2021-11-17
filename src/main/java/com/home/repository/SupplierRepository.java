package com.home.repository;

import com.home.repository.jpa.SupplierJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SupplierRepository {

    private final SupplierJpaRepository repository;

}
