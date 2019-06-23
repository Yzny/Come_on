package com.example.dell.come_on;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        final View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rck_Adapter.Vholder holder, final int position) {
        final User user = list.get(position);
        Glide.with(context).load(user.getUrl()).into(holder.iv);
        holder.tv.setText(user.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClik.add(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public Vholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
        }
    }
    SetOnClik setOnClik;

    public void setSetOnClik(SetOnClik setOnClik) {
        this.setOnClik = setOnClik;
    }

    interface SetOnClik{
        void add(int position);
    }
}
