package com.home.repository;

import com.home.entity.Supplier;
import com.home.repository.jpa.SupplierJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SupplierRepository {

    private final SupplierJpaRepository repository;

    public Page<Supplier> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("contactName"));
        Specification<Supplier> specification = null;

        return repository.findAll(specification, pageable);
    }

}
