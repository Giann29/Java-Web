package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDao;
import com.example.demo.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import com.example.demo.utils.JWTUtil;

import java.util.List;
@RestController
public class UserController{
    @Autowired
    private UserDao userDao;

    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        return userDao.getUser(id);
    }
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value="Authorization") String token){
        if (!validateToken(token)){
            return null;
        }
        return userDao.getUsers();
    }

    private boolean validateToken(String token){
        String userID = jwtUtil.getKey(token);
        return userID != null;
    }
    
    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUsers(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.registerUser(user);
    }

    //     @RequestMapping(value = "user/{id}")
    // public User editUser(@PathVariable Long id){
    //     User user = new User(id);
    //     user.setName("prueba");
    //     user.setPassword("<PASSWORD>");
    //     return user;
    // }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if (!validateToken(token)){
            return;
        }
        userDao.deleteUser(id);
    }
}
