package com.home.repository;

import com.home.repository.jpa.CustomerJpaRepository;
import com.home.repository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository repository;

}
