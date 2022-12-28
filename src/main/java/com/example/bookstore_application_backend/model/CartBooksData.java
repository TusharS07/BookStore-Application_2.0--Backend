package com.example.bookstore_application_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartBooksData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int cartBookId;

    private int quantity;

    private double totalPrice;

    @ManyToOne()
    @JoinColumn(name = "cartId")
    private CartModel cart;

    @ManyToOne()
    @JoinColumn(name = "bookId")
    private BookModel books;

    public CartBooksData(double totalPrice, CartModel cart, BookModel books) {
        this.totalPrice = totalPrice;
        this.cart = cart;
        this.books = books;
    }
}
