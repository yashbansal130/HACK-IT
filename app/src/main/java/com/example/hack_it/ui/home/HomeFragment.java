package com.example.hack_it.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hack_it.R;
import com.example.hack_it.RecyclerData;
import com.example.hack_it.RecyclerViewAdapter;
import com.example.hack_it.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    View.OnClickListener listener;
    ImageView like;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView=(RecyclerView) root.findViewById(R.id.idCourseRV);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new RecyclerData("Puma",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Jordan",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Nike",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Adidas",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Reebok",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Puma",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Jordan",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Nike",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Adidas",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Reebok",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Puma",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Jordan",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Nike",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Adidas",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Reebok",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Puma",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Jordan",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Nike",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Adidas",R.drawable.shoes));
        recyclerDataArrayList.add(new RecyclerData("Reebok",R.drawable.shoes));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,container.getContext());
//
        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager( container.getContext(),2);
//
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}