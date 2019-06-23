package com.example.myone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myone.dao.MyUtils;
import com.example.myone.presenter.Presenter_Main;
import com.example.myone.view.IView;

import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment implements IView {

    private RecyclerView rck;
    private List<User> list;
    private Rck_Adapter adapter;
    private Presenter_Main main;
    private int a;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rckmain, container, false);
        rck = view.findViewById(R.id.rck);
        rck.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter = new Rck_Adapter(getContext(), list);
        rck.setAdapter(adapter);
        adapter.setSetOnLongClik(new Rck_Adapter.SetOnLongClik() {
            @Override
            public void app(int position) {
                     a=position;
            }
        });
        main = new Presenter_Main(this);
        main.onNate();
        registerForContextMenu(rck);
        return view;
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,11,111,"收藏");
        menu.add(1,12,111,"删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final User user = list.get(a);
        switch (item.getItemId()){
            case 11:
                new MyUtils().insert(user);
                Snackbar.make(rck,"收藏成功!",Snackbar.LENGTH_SHORT).show();
                break;
            case 12:
                list.remove(a);
                Snackbar.make(rck,"删除成功!",Snackbar.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onSuccess(User user) {
        list.add(user);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String string) {

    }


}
