package com.example.myone.model;

import com.example.myone.ApiService;
import com.example.myone.User;
import com.example.myone.presenter.CallBackUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model_Mian implements ModelUI {
    @Override
    public void onData(final CallBackUI<User> callBackUI) {
        final Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.Url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiService service = build.create(ApiService.class);
        service.getlists()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            final String string = responseBody.string();
                            final JSONObject jsonObject = new JSONObject(string);
                            final JSONArray results = jsonObject.optJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                final JSONObject object = results.optJSONObject(i);
                                final String url = object.optString("url");
                                final String desc = object.optString("desc");
                                final User user = new User();
                                user.setDesc(desc);
                                user.setUrl(url);
                                callBackUI.onRetrofit(user);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}