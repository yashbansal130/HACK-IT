package com.example.hack_it;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<RecyclerData> courseDataArrayList;
    private Context mcontext;
    private ArrayAdapter<String> dataAdapter;
    private ArrayList<Pair<String, String>> wishlist;
    private ArrayList<String> groups;

    public RecyclerViewAdapter(ArrayList<RecyclerData> recyclerDataArrayList, Context mcontext, ArrayList<android.util.Pair<String, String>> group) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
        this.wishlist = group;
        groups = new ArrayList<>();
        for(int i=0;i<group.size();i++){
            groups.add(group.get(i).first);
        }
        dataAdapter = new ArrayAdapter<String>(mcontext,
                android.R.layout.simple_spinner_item,this.groups);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }



    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        RecyclerData recyclerData = courseDataArrayList.get(position);
        holder.courseTV.setText(recyclerData.getName());
        Picasso.get().load(recyclerData.getImageurl()).into(holder.courseIV);
        holder.spinner.setAdapter(dataAdapter);
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView courseTV;
        private ImageView courseIV;
        private Spinner spinner;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.idTVCourse);
            courseIV = itemView.findViewById(R.id.idIVcourseIV);
            itemView.setOnClickListener(this);
            spinner=itemView.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String id= wishlist.get(i).second;
                    //Toast.makeText(adapterView.getContext(), "Selected: " + id, Toast.LENGTH_SHORT).show();
                    postItem();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(adapterView.getContext(), "hello", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mcontext.getApplicationContext(),Integer.toString(getAdapterPosition()),Toast.LENGTH_SHORT).show();

        }
    }

    private void postItem(){

    }

}
