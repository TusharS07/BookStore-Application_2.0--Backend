package com.example.bookstore_application_backend.repository;

import com.example.bookstore_application_backend.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {
    List<BookModel> findByBookName(String bookName);
    List<BookModel> findAllByAuthorName(String authorName);
}
