package com.example.instrumentationtesting;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instrumentationtesting.db.UserEntity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

     private List<UserEntity> userList;
     private Context context;
     private ItemClicked itemClicked;


     public RecyclerViewAdapter(Activity context,List<UserEntity> data, ItemClicked itemClicked){
        this.context=context;
        this.userList=data;
        this.itemClicked = itemClicked;
        notifyDataSetChanged();
     }


    @NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder,int position){

        String name=userList.get(position).getName();
        String address=userList.get(position).getAddress();
        holder.setData(name,address);
        holder.deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked.deleteClicked(userList.get(position));
                //Log.e("TAG", "onClick: Delete ");
            }
        });
        }

@Override
public int getItemCount(){
        return userList.size();
        }


    public class ViewHolder extends RecyclerView.ViewHolder {

    TextView name1, address1;
    ImageView deleteUser;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name1 = itemView.findViewById(R.id.name);
        address1 = itemView.findViewById(R.id.address);
        deleteUser = itemView.findViewById(R.id.delete_image);
    }

    public void setData(String name, String address) {
        name1.setText(name);
        address1.setText(address);

    }
}
    public interface ItemClicked{
        void updateClicked(UserEntity userEntity);
        void deleteClicked(UserEntity userEntity);
    }

}
