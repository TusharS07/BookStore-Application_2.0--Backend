package com.example.bookstore_application_backend.service;

import com.example.bookstore_application_backend.dto.LoginDTO;
import com.example.bookstore_application_backend.dto.RegisterDTO;
import com.example.bookstore_application_backend.dto.UpdateDTO;
import com.example.bookstore_application_backend.exception.BookStoreException;
import com.example.bookstore_application_backend.model.UserModel;
import com.example.bookstore_application_backend.repository.UserRepository;
import com.example.bookstore_application_backend.utility.EmailService;
import com.example.bookstore_application_backend.utility.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IuserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtUtils jwtUtils;

    //--------------------------------- Register New User (Only User)---------------------------------
    @Override
    public String registerNewUser(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.getEmail()) == null) {
            UserModel registerNewUser = modelMapper.map(registerDTO,UserModel.class);
            registerNewUser.setRole("user");
            userRepository.save(registerNewUser);
            emailService.sendMail(registerDTO.getEmail(), "Registration Successful for BookStore Application");
            return "User Register Successful";
        }
        throw  new BookStoreException("User Already Exist"+"\n Please Try with Another Email id");
    }

    //--------------------------------- User Login (Both Admin or User)---------------------------------

    @Override
    public String[] login(LoginDTO loginDTO) {
        UserModel userModel = userRepository.findByEmail(loginDTO.getEmail());
        if (userModel != null) {
            if (userModel.getPassword().equals(loginDTO.getPassword())) {
                String [] obj = new String[2];
                String token = jwtUtils.generateToken(loginDTO);
                userModel.setLogin(true);
                userModel.setId(userModel.getId());
                userRepository.save(userModel);
                emailService.sendMail(loginDTO.getEmail(), "Login Successful");
                obj[0] = token;
                obj[1] = userModel.getRole();
                return obj;
            }
            throw new BookStoreException("please check Your Password");
        }
        throw new BookStoreException("Check Your Email-ID");
    }

    //--------------------------------- User Logout (Both Admin or User)---------------------------------
    @Override
    public String logout(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel userModel = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (userModel.isLogin()) {
            userModel.setLogin(false);
            userRepository.save(userModel);
            return "User Logout Successfully";
        }
        throw new BookStoreException("User Not Found");
    }
    //--------------------------------- Forgot Password (Both Admin or User)---------------------------------
    @Override
    public String forgotPassword(String token, String password) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel resetPassword = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (resetPassword.isLogin()) {
            resetPassword.setPassword(password);
            resetPassword.setLogin(false);
            userRepository.save(resetPassword);
            return "Password changed Successful";
        }
        throw new BookStoreException("please Login with Proper email and password");
    }

    //--------------------------------- Delete User Data (Only User)---------------------------------

    @Override
    public String delete(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel delete = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (delete.isLogin()) {
            userRepository.deleteById(delete.getId());
            return "user deleted";
        }
        throw new BookStoreException("User Not Found");
    }

    //--------------------------------- Delete User (Only Admin)---------------------------------

    @Override
    public String deleteUserAsAdmin(String token, int id) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel User = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (User.getRole().equals("Admin") && User.isLogin()) {
            if (userRepository.findById(id).isPresent()) {
                userRepository.deleteById(id);
                return "user deleted";
            }
            throw new BookStoreException("User Not Found");
        }
        throw new BookStoreException("Please sign in your account as Admin");
    }

    //--------------------------------- Update User Data (Both Admin or User)---------------------------------

    @Override
    public UserModel update(UpdateDTO updateDTO, String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (user != null) {
            UserModel update = modelMapper.map(updateDTO, UserModel.class);
            update.setId(user.getId());
            update.setLogin(true);
            update.setRole(user.getRole());
            if (update.getFirstName() == null) {
                update.setFirstName(user.getFirstName());
            }
            if (update.getLastName() == null) {
                update.setLastName(user.getLastName());
            }
            if (update.getEmail() == null) {
                update.setEmail(user.getEmail());
            }
            if (update.getPassword() == null) {
                update.setPassword(user.getPassword());
            }
            return userRepository.save(update);
        }
        throw new BookStoreException("Email And Password is not Matched");
    }

    //--------------------------------- Get User Data (Only User)---------------------------------
    @Override
    public UserModel getUserData(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (user.isLogin()) {
            return userRepository.findById(user.getId()).get();
        }
        throw new BookStoreException("Invalid User");
    }

    //----------------------------- Show_All_UserData (Only Admin)--------------------------------

    @Override
    public List<UserModel> showAllUsers(String token) {
        LoginDTO loginDTO = jwtUtils.decodeToken(token);
        UserModel user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (user.isLogin()) {
            if (user.getRole().equals("Admin")) {
                return userRepository.findAll();
            }
            throw new BookStoreException("Not Accessable to You");
        }
        throw new BookStoreException("Admin is not Login");
    }
}
