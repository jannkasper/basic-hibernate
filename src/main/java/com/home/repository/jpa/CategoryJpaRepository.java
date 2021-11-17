package com.home.repository.jpa;

import com.home.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Integer>,
        JpaSpecificationExecutor<Category> {

    public Category findTop1ByOrderByIdDesc();

    public List<Category> findTop3ByOrderByIdDesc();

    public List<Category> findCategoryByDescriptionIsLikeIgnoreCase(String description);

    @Query(value = "SELECT c FROM Category c WHERE c.name = :categoryName")
    public List<Category> findByCategoryName(@Param("categoryName") String name);

    @Query(value = "SELECT * FROM CATEGORIES WHERE CATEGORYID IN (SELECT CATEGORYID FROM PRODUCTS GROUP BY CATEGORYID ORDER BY COUNT(CATEGORYID) LIMIT :limit)", nativeQuery = true)
    public List<Category> findTheMostPopularCategoryNative(@Param("limit") Integer limit);


}
