package com.example.myone.presenter;

public interface CallBackUI <T>{
    void onRetrofit(T t);
    void onpush(String string);
}
