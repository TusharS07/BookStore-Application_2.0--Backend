package com.example.bookstore_application_backend.repository;

import com.example.bookstore_application_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    UserModel findByEmail(String email);
    UserModel findByEmailAndPassword(String email, String password);
}
