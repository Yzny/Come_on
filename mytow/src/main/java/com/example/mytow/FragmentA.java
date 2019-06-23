package com.example.mytow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.mytow.dao.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment {

    private RecyclerView rck;
    private List<User> list;
    private Rck_Adapter adapter;
    private CheckBox ckb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.a_main, container, false);
        rck = view.findViewById(R.id.rck);
        rck.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final User user = new User();
            user.setName("haha"+i);
            list.add(user);
        }
        adapter = new Rck_Adapter(getContext(), list);
        rck.setAdapter(adapter);
        adapter.setSetOnclik(new Rck_Adapter.SetOnclik() {
            @Override
            public void add(int position, View view) {
                ckb = view.findViewById(R.id.ckbox);
                if (ckb.isChecked()){
                    final User user = list.get(position);
                    new MyUtils().insert(user);
                    Snackbar.make(rck,"添加成功!",Snackbar.LENGTH_SHORT).show();
                }else {
                    final User user = list.get(position);
                    new MyUtils().delete(user);
                    Snackbar.make(rck,"删除成功!",Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&adapter!=null){
            adapter.refres();
        }
    }
}
