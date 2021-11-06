package com.example.hack_it;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hack_it.ui.WishListActivity;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {

    ArrayList<WishlistData> groupNames;
    Context context;
    public WishlistAdapter(Context context, ArrayList<WishlistData> groupNames) {
        this.context = context;
        this.groupNames = groupNames;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_group_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // set the data in items
        WishlistData wishlistData = groupNames.get(position);
        holder.groupname.setText(wishlistData.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                //Toast.makeText(context, Integer.toString(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, GroupActivity.class);
                intent.putExtra("data", groupNames.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView groupname;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            groupname=itemView.findViewById(R.id.group_name);
        }
    }
}

