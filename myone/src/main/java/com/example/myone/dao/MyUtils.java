package com.example.myone.dao;



import com.example.myone.User;

import java.util.List;

public class MyUtils {
    public void insert(User user){
        final DaoSession session = Myapp.getSession();
        session.getUserDao().insert(user);
    }
    public void delete(User user){
        final DaoSession session = Myapp.getSession();
        session.getUserDao().delete(user);
    }
    public static List<User> show(){
        final DaoSession session = Myapp.getSession();
        final List<User> users = session.loadAll(User.class);
        return users;
    }
}
