package com.example.bookstore_application_backend.repository;

import com.example.bookstore_application_backend.model.CartBooksData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBooksRepository extends JpaRepository<CartBooksData, Integer> {

    @Query(value = "SELECT * FROM bookstore_application_2.cart_books_data where cart_id = :cartId and book_id = :bookId", nativeQuery = true)
    CartBooksData findByCartIdAndBookId(int cartId, int bookId);

    List<CartBooksData> findByCart_CartId(int cartId);
}
