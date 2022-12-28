package com.example.bookstore_application_backend.repository;

import com.example.bookstore_application_backend.model.CartModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {

}
