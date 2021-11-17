package com.home.repository;

import com.home.repository.jpa.CustomerJpaRepository;
import com.home.repository.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderJpaRepository repository;

}
