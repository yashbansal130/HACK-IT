package com.example.hack_it.ui;

import android.content.Intent;
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
import com.example.hack_it.NewGroupActivity;
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

import org.json.JSONArray;
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
    WishlistAdapter customAdapter;
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
                Intent intent = new Intent(WishListActivity.this, NewGroupActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        volleyGet();


    }
    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            volleyPost(message);
        }
    }
    public void volleyPost(String name){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String postUrl = "http://192.168.1.10:5000/wishlist";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                customAdapter.notifyItemInserted(groupNames.size()-1);
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
                params.put("id", FirebaseAuth.getInstance().getCurrentUser().getUid());
                params.put("groupname", name);
                return params;
            }

        };
        requestQueue.add(jsonObjectRequest);
    }
    public void volleyGet(){
        String url="http://192.168.1.10:5000/users/"+FirebaseAuth.getInstance().getCurrentUser().getUid();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("info", response.toString());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("wishlist");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("info", jsonArray.toString());
                        for(int i=0;i<jsonArray.length();i++){
                            try {
                                JSONObject temp = jsonArray.getJSONObject(i);
                                String group = temp.getString("groupname");
                                groupNames.add(new WishlistData(group));
                                Log.i("name", group);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerView = findViewById(R.id.recycler_wishlist);
                        customAdapter = new WishlistAdapter(WishListActivity.this, groupNames);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(customAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}