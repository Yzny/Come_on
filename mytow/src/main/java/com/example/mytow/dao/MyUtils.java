package com.example.mytow.dao;

import com.example.mytow.User;

import java.util.List;

public class MyUtils {
    public static User CreateView(User user){
        final User unique = Myapp.getSession().queryBuilder(User.class).where(UserDao.Properties.Name.eq(user.getName())).build().unique();
        return unique;
    }

    public void insert (User user){
        DaoSession session = Myapp.getSession();
        if (CreateView(user)==null){
            session.insert(user);
        }

    }
    public void delete (User user){
        DaoSession session = Myapp.getSession();
        final User user1 = CreateView(user);
        if (user1!=null){
            session.delete(user1);
        }

    }
    public static List<User> show(){
        final DaoSession session = Myapp.getSession();
        final List<User> users = session.loadAll(User.class);
        return users;
    }
}

