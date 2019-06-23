package com.example.myone.presenter;

import com.example.myone.User;
import com.example.myone.model.Model_Mian;
import com.example.myone.view.IView;

public class Presenter_Main implements PresenterUI{

    private Model_Mian mian;
    IView view;

    public Presenter_Main(IView view) {
        mian = new Model_Mian();
        this.view=view;
    }

    @Override
    public void onNate() {
        mian.onData(new CallBackUI<User>() {
            @Override
            public void onRetrofit(User user) {
                view.onSuccess(user);
            }

            @Override
            public void onpush(String string) {

            }
        });
    }
}
