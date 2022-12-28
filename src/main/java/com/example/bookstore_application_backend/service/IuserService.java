package com.example.bookstore_application_backend.service;


import com.example.bookstore_application_backend.dto.LoginDTO;
import com.example.bookstore_application_backend.dto.RegisterDTO;
import com.example.bookstore_application_backend.dto.UpdateDTO;
import com.example.bookstore_application_backend.model.UserModel;

import java.util.List;

public interface IuserService {
    String registerNewUser(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
    String logout(String token);
    String forgotPassword(String token, String password);
    String delete(String token);
    String deleteUserAsAdmin(String token, int id);

    UserModel update(UpdateDTO updateDTO, String token);

    UserModel getUserData(String token);
    List<UserModel> showAllUsers(String token);

}
