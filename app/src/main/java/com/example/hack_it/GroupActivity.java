package com.example.hack_it;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {

    private ArrayList<RecyclerData> recyclerDataArrayList;
    JSONArray itemArray;
    RequestQueue queue;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Intent intent = getIntent();
        String id = intent.getStringExtra("data");
        recyclerDataArrayList = new ArrayList<>();
        queue = Volley.newRequestQueue(GroupActivity.this);
        getData(id);
        recyclerView = findViewById(R.id.groupRV);

    }

    private void getData(String id) {
        String url = "http://192.168.1.10:3000/wishlist/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("msg");
                    itemArray=jsonObject.getJSONArray("selectedItems");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
        getAll();
    }
    private void getAll() {
        String url = "http://192.168.1.10:3000/items/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject result = null;
                        try {
                            result = new JSONObject(response);
                            if(itemArray!=null){
                                for(int i=0;i<itemArray.length();i++){
                                    JSONObject jsonObject = itemArray.getJSONObject(i);
                                    String itemId = jsonObject.getString("itemId");
                                    JSONObject itemJson =result.getJSONObject(itemId);
                                    String id = itemJson.getString("id");
                                    String name = itemJson.getString("name");
                                    setTitle(name);
                                    String imageurl = itemJson.getString("imageurl");
                                    recyclerDataArrayList.add(new RecyclerData(id, name, imageurl));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(recyclerDataArrayList.size()!=0){
                            GroupAdapter adapter=new GroupAdapter(recyclerDataArrayList,GroupActivity.this);
                            GridLayoutManager layoutManager=new GridLayoutManager( GroupActivity.this,2);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(stringRequest);
    }

}