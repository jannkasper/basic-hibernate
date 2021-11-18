package com.home.repository.jpa;

import com.home.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT p FROM Product p INNER JOIN FETCH p.category c INNER JOIN FETCH p.supplier s ")
    List<Product> findAllFullFetch();

}
