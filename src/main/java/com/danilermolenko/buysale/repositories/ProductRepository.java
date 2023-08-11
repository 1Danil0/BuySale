package com.danilermolenko.buysale.repositories;

import com.danilermolenko.buysale.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String title);
}
