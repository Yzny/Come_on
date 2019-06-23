package com.example.myone;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myone.dao.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class ShouCang extends AppCompatActivity {

    private RecyclerView rck;
    private List<User> list;
    private Rck2_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        rck = findViewById(R.id.rck2);
        rck.setLayoutManager(new LinearLayoutManager(this));
        list = MyUtils.show();
        adapter = new Rck2_Adapter(this, list);
        rck.setAdapter(adapter);
        adapter.setSetOnClik(new Rck2_Adapter.SetOnClik() {
            @Override
            public void add(int position) {
                final User user = list.get(position);
                new MyUtils().delete(user);
                    final NotificationManager service = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        final NotificationChannel channel = new NotificationChannel("1", "aa", NotificationManager.IMPORTANCE_DEFAULT);
                        service.createNotificationChannel(channel);
                    }
                    final Notification build = new NotificationCompat.Builder(ShouCang.this, "1")
                            .setSmallIcon(R.mipmap.aa)
                            .setAutoCancel(true)
                            .setContentTitle("数据库通知")
                            .setContentText("删除成功!")
                            .build();
                    service.notify(1,build);

                list.remove(position);
                adapter.notifyDataSetChanged();

            }
        });
        adapter.notifyDataSetChanged();
    }
}
