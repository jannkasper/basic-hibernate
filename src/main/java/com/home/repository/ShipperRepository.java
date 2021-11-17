package com.home.repository;

import com.home.repository.jpa.CustomerJpaRepository;
import com.home.repository.jpa.ShipperJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShipperRepository {

    private final ShipperJpaRepository repository;

}
