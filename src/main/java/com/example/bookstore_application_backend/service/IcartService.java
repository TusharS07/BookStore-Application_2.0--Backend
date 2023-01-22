package com.example.bookstore_application_backend.service;



import com.example.bookstore_application_backend.model.CartBooksData;

import java.util.List;


public interface IcartService {

    CartBooksData addToCart(String token, int bookId); //Add Books Into Cart

    CartBooksData increaseBookQty(String token, int bookId); //add 1 qty of books into cart

    CartBooksData decreaseBookQty(String token, int bookId); //remove 1 qty of books into cart

    String removeBookFromCart(String token, int cartBookId); // remove Book From Cart

    List<CartBooksData> showCartRecord(String token);
    double [] showTotalAmount_Qty(String token);



}
