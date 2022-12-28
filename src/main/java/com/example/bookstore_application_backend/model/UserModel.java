package com.example.bookstore_application_backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isLogin = false;
    private String role ;


    private String phoneNo;
    private String pinCode;
    private String locality;
    private String address;
    private String city;
    private String landMark;
    private String AddressType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private CartModel cartModel;


}
