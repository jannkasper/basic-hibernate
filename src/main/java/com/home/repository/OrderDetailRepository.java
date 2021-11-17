package com.home.repository;

import com.home.repository.jpa.CustomerJpaRepository;
import com.home.repository.jpa.OrderDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDetailRepository {

    private final OrderDetailJpaRepository repository;

}
