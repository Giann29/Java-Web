package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(Long id) {
        User user = entityManager.find(User.class, id);
        return user;    
    }
    
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        String queryString = "FROM User";
        return entityManager.createQuery(queryString).getResultList();
    }

    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }


    public void registerUser(User user) {
        entityManager.merge(user);
    }

    @SuppressWarnings("unchecked")
    public User verifyUser(User user) {
        String queryString = "FROM User WHERE email = :email ";
        List<User> users = entityManager.createQuery(queryString)
        .setParameter("email", user.getEmail())
        .getResultList();
        if (users.isEmpty()){
            return null;
        }
        String hashedPassword = users.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(hashedPassword, user.getPassword())){
            return users.get(0);
        }
        return null;
    }
    
}
