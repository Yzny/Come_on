package com.example.myone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Rck_Adapter extends RecyclerView.Adapter<Rck_Adapter.Vholder> {
    private Context context;
    private List<User>mList;

    public Rck_Adapter(Context context, List<User> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Rck_Adapter.Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rck_Adapter.Vholder holder, final int position) {
        final User user = mList.get(position);
        final RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(context).load(user.getUrl()).into(holder.iv);
        holder.tv.setText(user.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClik.add(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setOnLongClik.app(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
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
    SetOnLongClik setOnLongClik;

    public void setSetOnLongClik(SetOnLongClik setOnLongClik) {
        this.setOnLongClik = setOnLongClik;
    }

    public void setSetOnClik(SetOnClik setOnClik) {
        this.setOnClik = setOnClik;
    }

    interface SetOnClik{
        void add(int position);
    }
    interface SetOnLongClik{
        void app(int position);
    }
}
