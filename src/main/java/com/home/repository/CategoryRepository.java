package com.home.repository;

import com.home.entity.Category;
import com.home.repository.jpa.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final CategoryJpaRepository repository;

    public Page<Category> findAll() {

        Specification<Category> specification = getCondiments();

        Pageable page = Pageable.unpaged();

        return repository.findAll(specification, page);
    }

    private static Specification<Category> getCondiments() {
        return (root, query, builder) -> {
            return builder.equal(root.get("name"),"Condiments");
        };
    }

}
