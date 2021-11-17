package com.home.repository.jpa;

import com.home.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Integer> {
}
