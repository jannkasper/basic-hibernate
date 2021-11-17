package com.home.repository.jpa;

import com.home.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperJpaRepository extends JpaRepository<Shipper, Integer> {
}
