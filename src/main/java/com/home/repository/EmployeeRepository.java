package com.home.repository;

import com.home.repository.jpa.EmployeeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final EmployeeJpaRepository repository;

}
