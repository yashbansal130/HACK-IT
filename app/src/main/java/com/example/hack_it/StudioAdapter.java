package com.example.hack_it;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.StudioViewHolder> {

    Context mcontext;
    ArrayList<StudioData> studioDataArrayList;

    public StudioAdapter(ArrayList<StudioData> studioDataArrayList, Context mcontext) {
        this.studioDataArrayList = studioDataArrayList;
        this.mcontext = mcontext;

    }
    @NonNull
    @Override
    public StudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studio_item, parent, false);
        return new StudioAdapter.StudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudioViewHolder holder, int position) {
        StudioData studioData = studioDataArrayList.get(position);
        holder.studioName.setText(studioData.getName());
        holder.studioImage.setImageResource(studioData.getImage());
        holder.studioCaption.setText(studioData.getCaption());
    }



    @Override
    public int getItemCount() {
        return studioDataArrayList.size();
    }
    // View Holder Class to handle Recycler View.
    public class StudioViewHolder extends RecyclerView.ViewHolder{

        private TextView studioName;
        private ImageView studioImage;
        private TextView studioCaption;

        public StudioViewHolder(@NonNull View itemView) {
            super(itemView);
            studioName = itemView.findViewById(R.id.studio_name);
            studioImage = itemView.findViewById(R.id.studio_image);
            studioCaption = itemView.findViewById(R.id.studio_caption);
        }
    }
}







