package com.example.hack_it.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hack_it.R;
import com.example.hack_it.RecyclerData;
import com.example.hack_it.RecyclerViewAdapter;
import com.example.hack_it.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    View.OnClickListener listener;
    ImageView like;
    private RequestQueue queue;
    String url = "http://192.168.1.10:5000/items/";
    ViewGroup viewGroup;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();
        viewGroup=container;

        recyclerView=(RecyclerView) root.findViewById(R.id.idCourseRV);
        queue = Volley.newRequestQueue(this.getContext());
        queue.add(stringRequest);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject result = null;
                    try {
                        result = new JSONObject(response);
                        JSONArray jsonArray =result.names();
                        int n=jsonArray.length();
                        Log.i("length", Integer.toString(n));
                        for(int i=0;i<n;i++){
                            JSONObject jsonObject= (JSONObject) result.get(result.names().getString(i));
                            String id=jsonObject.getString("id");
                            String name=jsonObject.getString("name");
                            String imageurl=jsonObject.getString("imageurl");
                            Log.i("hi", name);
                            recyclerDataArrayList.add(new RecyclerData(id, name, imageurl));
                        }
                        // added data from arraylist to adapter class.
                        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,viewGroup.getContext());
//
                        // setting grid layout manager to implement grid view.
                        // in this method '2' represents number of columns to be displayed in grid view.
                        GridLayoutManager layoutManager=new GridLayoutManager( viewGroup.getContext(),2);
//
                        // at last set adapter to recycler view.
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("error", error.toString());
                }
            });
}