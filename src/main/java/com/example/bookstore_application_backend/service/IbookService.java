package com.example.bookstore_application_backend.service;



import com.example.bookstore_application_backend.dto.BookDTO;
import com.example.bookstore_application_backend.model.BookModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IbookService {
    BookModel addBooks(String token, BookDTO bookDTO);
    BookModel updateBooksData(String token, int id, BookDTO bookDTO);
    String deleteBookById(String token, int id);
    List<BookModel> showAllBooks();
    BookModel getBookByID(int id);
    List<BookModel> searchBookByName(String bookName);
    List<BookModel> searchBookByAuthorName(String authorName);
    List<BookModel> sortBookByPriceHighToLow();
    List<BookModel> sortBookByPriceLowToHigh();
    List<BookModel> sortBooksByNewestArrivals();
}
