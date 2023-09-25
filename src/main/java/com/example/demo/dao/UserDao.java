package com.example.demo.dao;
import java.util.List;

import com.example.demo.models.User;
public interface UserDao {

    List<User> getUsers();
    User getUser(Long id);
    void deleteUser(Long id);
    void registerUser(User user);
    User verifyUser(User user);
}
