package com.example.myone.model;

import com.example.myone.User;
import com.example.myone.presenter.CallBackUI;

public interface ModelUI {
    void onData(CallBackUI<User> callBackUI);
}
