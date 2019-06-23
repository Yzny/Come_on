package com.example.mytow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mytow.dao.MyUtils;

import java.util.List;

public class Rck_Adapter extends RecyclerView.Adapter<Rck_Adapter.Vholder> {
    private Context context;
    private List<User>list;

    public Rck_Adapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Rck_Adapter.Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.a_item, parent, false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rck_Adapter.Vholder holder, final int position) {
        final User user = list.get(position);
        holder.tv.setText(user.getName());
        holder.ckb.setChecked(user.getIsChecked());
        final User user1 = MyUtils.CreateView(user);
        if (user1==null){
            holder.ckb.setChecked(false);
        }else {
            holder.ckb.setChecked(true);
        }
        holder.ckb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnclik.add(position,view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refres() {
        notifyDataSetChanged();
    }

    public class Vholder extends RecyclerView.ViewHolder {
        TextView tv;
        CheckBox ckb;
        public Vholder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            ckb=itemView.findViewById(R.id.ckbox);
        }
    }
    SetOnclik setOnclik;

    public void setSetOnclik(SetOnclik setOnclik) {
        this.setOnclik = setOnclik;
    }

    interface SetOnclik{
        void add(int position,View view);
    }
}
