package com.example.dell.come_on.dao;

import com.example.dell.come_on.User;

import java.util.List;

public class MyUtils {
    public void inserinto(User user){

        final DaoSession session = Myapp.getSession();
        session.insert(user);
    }
    public void delete(User user){
        final DaoSession session = Myapp.getSession();
        session.delete(user);
    }
    public static List<User> show(){
        final DaoSession session = Myapp.getSession();
        final List<User> users = session.loadAll(User.class);
        return users;
    }
}
