package com.example.hack_it;
import android.content.Context;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {

    private ArrayList<RecyclerData> courseDataArrayList;
    private Context mcontext;


    public GroupAdapter(ArrayList<RecyclerData> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }



    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_card_layout, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        // Set the data to textview and imageview.
        RecyclerData recyclerData = courseDataArrayList.get(position);
        holder.textView.setText(recyclerData.getName());
        Picasso.get().load(recyclerData.getImageurl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private ImageView image;


        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.group_textview);
            image = itemView.findViewById(R.id.group_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mcontext.getApplicationContext(),Integer.toString(getAdapterPosition()),Toast.LENGTH_SHORT).show();

        }
    }
}
