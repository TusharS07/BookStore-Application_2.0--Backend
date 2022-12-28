package com.example.bookstore_application_backend.repository;

import com.example.bookstore_application_backend.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

    List<OrderModel> findAllByUserId(int userId);
}
