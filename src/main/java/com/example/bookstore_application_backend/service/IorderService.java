package com.example.bookstore_application_backend.service;


import com.example.bookstore_application_backend.dto.OrderDTO;
import com.example.bookstore_application_backend.model.OrderModel;

import java.util.List;

public interface IorderService {

    OrderModel placeOrder(String token, OrderDTO orderDTO);
    List<OrderModel> showUserAllOrders(String token);

    String cancelOrder(String token, int orderId);

    OrderModel changeMobileNo(String token, int orderId, String mobNo);

    OrderModel getOrderDetailsByOrderIdForUser(String token, int orderId);

    OrderModel getOrderDetailsByOrderIdForAdmin(String token, int orderId);  //Admin

    List<OrderModel> adminGetAllOrderDetails(String token); //Admin
}
