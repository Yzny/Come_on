package com.example.myone.view;

import com.example.myone.User;

public interface IView {
    void onSuccess(User user);
    void onFail(String string);
}
