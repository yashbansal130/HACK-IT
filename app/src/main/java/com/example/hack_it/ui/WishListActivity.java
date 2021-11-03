package com.example.hack_it.ui;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hack_it.MainActivity;
import com.example.hack_it.RecyclerViewAdapter;
import com.example.hack_it.WishlistAdapter;
import com.example.hack_it.WishlistData;
import com.example.hack_it.databinding.ActivityWishListBinding;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hack_it.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WishListActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityWishListBinding binding;
    RecyclerView recyclerView;
    ArrayList<WishlistData> groupNames = new ArrayList<WishlistData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityWishListBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volleyPost();
            }
        });
        groupNames.add(new WishlistData("person 1"));
        groupNames.add(new WishlistData("person 2"));
        groupNames.add(new WishlistData("person 3"));
        recyclerView = findViewById(R.id.recycler_wishlist);
        RecyclerView.Adapter customAdapter = new WishlistAdapter(WishListActivity.this, groupNames);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);

    }
    public void volleyPost(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String postUrl = "http://192.168.1.10:5000/"+user.getUid();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };
        requestQueue.add(jsonObjectRequest);
    }

}