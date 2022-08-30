package com.golovin.springboot.springboot311.dao;

import com.golovin.springboot.springboot311.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {



    @PersistenceContext
    private EntityManager entityManger;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Query query = entityManger.createQuery("select u from User u ", User.class);
            return query.getResultList();}

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            entityManger.persist(user);
        } else {
            entityManger.merge(user);
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        User userToBeUpdate = entityManger.find(User.class,id);
        userToBeUpdate.setFirstName(user.getFirstName());
        userToBeUpdate.setLastName(user.getLastName());
        userToBeUpdate.setUserCity(user.getUserCity());

    }

    @Override
    public void deleteUser(Long id) {
        entityManger.remove(getUser(id));
    }

    @Override
    public User getUser(Long id) {
        User user;
        user = entityManger.find(User.class, id);
        return user;
    }

}
