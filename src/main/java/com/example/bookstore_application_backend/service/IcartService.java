package com.example.bookstore_application_backend.service;



import com.example.bookstore_application_backend.model.CartBooksData;

import java.util.List;


public interface IcartService {

    CartBooksData addToCart(String token, int bookId); //Add Books Into Cart

    CartBooksData updateBooksOfCart(String token, int bookId, int qty); //add more qty of books into cart

    String removeBookFromCart(String token, int cartBookId); // remove Book From Cart

    List<CartBooksData> showCartRecord(String token);


}
