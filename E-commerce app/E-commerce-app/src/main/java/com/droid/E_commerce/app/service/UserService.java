package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void createUser(User user);

    User findUserByEmail(String email);

    void updateUser(User user);
    void deleteUser(Long id);
    boolean verifyCredentials(String email, String password);
}
