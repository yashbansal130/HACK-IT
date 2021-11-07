package com.example.hack_it;
import android.content.Context;
import android.util.Log;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        groups.add("Choose Group");
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
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV;
        private ImageView courseIV;
        private Spinner spinner;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.idTVCourse);
            courseIV = itemView.findViewById(R.id.idIVcourseIV);
            spinner=itemView.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i>0){
                        String id= wishlist.get(i-1).second;
                        postItem( i-1, getAdapterPosition());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(adapterView.getContext(), "hello", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void postItem(int i,int pos){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String wishlistId = wishlist.get(i).second;
        String itemId = courseDataArrayList.get(pos).getId();
        String url = "http://192.168.1.10:5000/wishlist/additem";

        RequestQueue requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userID", user.getUid());
                params.put("groupID", wishlistId);
                params.put("itemID", itemId);
                Log.i("info", user.getUid()+" "+wishlistId+" "+itemId);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
