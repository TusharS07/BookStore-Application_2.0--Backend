package com.example.bookstore_application_backend.controller;

import com.example.bookstore_application_backend.Response;
import com.example.bookstore_application_backend.model.CartBooksData;
import com.example.bookstore_application_backend.service.IcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/CartPage")
@CrossOrigin("*")
public class CartController {

    @Autowired
    IcartService icartService;


    //--------------------------------- Add New Cart Data (Only User)---------------------------------
    @PostMapping("/AddToCart")
    public ResponseEntity<Response> addToCart(@RequestHeader String token, @RequestParam int bookId) {
        CartBooksData cart = icartService.addToCart(token, bookId);
        Response response = new Response(cart, "Books Added Into Cart Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Update Cart Data (Only User)---------------------------------
    @PutMapping("UpdateBookCart")
    public ResponseEntity<Response> updateBookCart(@RequestHeader String token, @RequestParam int bookId, @RequestParam int qty) {
        CartBooksData cart = icartService.updateBooksOfCart(token,bookId,qty);
        Response response = new Response(cart, "Book Quantity Update Into Cart Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Delete Cart Data (Only User)---------------------------------
    @DeleteMapping("/Remove_Book_From_Cart")
    public ResponseEntity<Response> removeBookFromCart(@RequestHeader String token, @RequestParam int cartBookId) {
        icartService.removeBookFromCart(token,cartBookId);
        Response response = new Response("Removed Book for id: " + cartBookId, "Book Remove Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Show Cart Data(Books) ---------------------------------
    @GetMapping("/Show_Cart_Record")
    public ResponseEntity<Response> showUserCartRecords(@RequestHeader String token) {
        List<CartBooksData> cartRecord = icartService.showCartRecord(token);
        Response response = new Response(cartRecord, "Cart record retrieved successfully for User");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
