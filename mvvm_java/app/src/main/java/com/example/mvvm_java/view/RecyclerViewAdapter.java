package com.example.mvvm_java.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvvm_java.R;
import com.example.mvvm_java.model.UserEntity;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<UserEntity> userList;
    private Context context;

    public RecyclerViewAdapter(List<UserEntity> data) {
        //this.context = (Context) context;
        this.userList = data;
        //notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int id = userList.get(position).getId();
        String name = userList.get(position).getName();
        String email = userList.get(position).getEmail();
        String gender = userList.get(position).getGender();
        String status = userList.get(position).getStatus();
        holder.setData(id, name, email, gender, status);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void getAllUsers(List<UserEntity> userList){
        this.userList = userList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id1, name1, email1, gender1, status1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id1 = itemView.findViewById(R.id.id);
            name1 = itemView.findViewById(R.id.name);
            email1 = itemView.findViewById(R.id.email);
            gender1 = itemView.findViewById(R.id.gender);
            status1 = itemView.findViewById(R.id.status);
        }

        public void setData(int id, String name, String email, String gender, String status) {
            id1.setText(String.valueOf(id));
            name1.setText(name);
            email1.setText(email);
            gender1.setText(gender);
            status1.setText(status);
        }
    }
}
