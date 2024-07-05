package com.app.productservice.repository;

import com.app.productservice.modals.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);
    List<Product> findByTitle(String title);
    List<Product> findByTitleContains(String str);

    @Override
    void delete(Product entity);

    Product save(Product entity);
}
