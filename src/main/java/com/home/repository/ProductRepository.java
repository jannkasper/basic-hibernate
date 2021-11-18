package com.home.repository;

import com.home.entity.Product;
import com.home.repository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository repository;

    public List<Product> fetchAll() {
        List<Product> list = repository.findAllFullFetch();
        return list;
    }

    public Page<Product> findAllByCategoryName() {
        Pageable page = Pageable.unpaged();

        Specification<Product> specification = joinCategory("Confections");

        Page<Product> list = repository.findAll(specification, page);

        return list;
    }

    public Page<Product> findAllBySupplierName() {
        Pageable page = Pageable.unpaged();

        Specification<Product> specification = joinSupplier("Specialty Biscuits, Ltd.");

        Page<Product> list = repository.findAll(specification, page);

        return list;
    }

    public Page<Product> findWithPrice(int min, int max) {
        Pageable page = Pageable.unpaged();

        Specification<Product> specification = betweenPrice(min, max);

        Page<Product> list = repository.findAll(specification, page);

        return list;
    }

    public Page<Product> findAllWithCategoryNameAndSupplierName(String categoryName, String supplierName) {
        Pageable page = Pageable.unpaged();

        Specification<Product> specification =
                joinCategory("Confections")
                .and(joinSupplier("Specialty Biscuits, Ltd."));

//        Specification<Product> specification = joinCategoryAndSupplier("Confections", "Specialty Biscuits, Ltd.");

        Page<Product> list = repository.findAll(specification, page);

        return list;
    }

    private static Specification<Product> betweenPrice(int min, int max) {
        return (root, query, builder) -> {
            Predicate right = builder.greaterThanOrEqualTo(root.get("price"), min);
            Predicate left = builder.lessThanOrEqualTo(root.get("price"), max);
            return builder.and(left, right);
        };
    }

    private static Specification<Product> joinCategory(String categoryName) {
        return (root, query, builder) -> {
            Join innerJoin = root.join("category", JoinType.INNER);

            return builder.equal(innerJoin.get("name"), categoryName);
        };
    }

    private static Specification<Product> joinSupplier(String supplierName) {
        return (root, query, builder) -> {
            Join innerJoin = root.join("supplier", JoinType.INNER);

            return builder.equal(innerJoin.get("name"), supplierName);
        };
    }

    private static Specification<Product> joinCategoryAndSupplier(String categoryName, String supplierName) {
        return (root, query, builder) -> {
            Join categoryJoin = root.join("category", JoinType.INNER);
            Join supplierJoin = root.join("supplier", JoinType.INNER);

            return builder.and(
                    builder.equal(categoryJoin.get("name"), categoryName),
                    builder.equal(supplierJoin.get("name"), supplierName)
            );
        };
    }

    private static Specification<Product> fetchEverything() {
        return (root, query, builder) -> {
            Fetch categoryFetch = root.fetch("category", JoinType.INNER);
            Fetch supplierFetch = root.fetch("supplier", JoinType.INNER);

            return builder.conjunction();
        };
    }

}
