package com.example.dell.come_on;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRck;
    private List<User> list;
    private Rck_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRck = findViewById(R.id.rck);
        mRck.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new Rck_Adapter(this, list);
        mRck.setAdapter(adapter);
        jiexi();
        adapter.setSetOnClik(new Rck_Adapter.SetOnClik() {
            @Override
            public void add(int position) {
                MyEvent myEvent=new MyEvent();
                myEvent.list=list;

                EventBus.getDefault().postSticky(myEvent);
                final Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    private void jiexi() {
        final Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.Url)
                .build();
        final ApiService service = build.create(ApiService.class);
        service.getlist()
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
                                list.add(user);
                                adapter.notifyDataSetChanged();
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
