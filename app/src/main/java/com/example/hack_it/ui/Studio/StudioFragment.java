package com.example.hack_it.ui.Studio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hack_it.Post;
import com.example.hack_it.R;
import com.example.hack_it.StudioAdapter;
import com.example.hack_it.StudioData;
import com.example.hack_it.databinding.FragmentStudioBinding;
import java.util.ArrayList;

public class StudioFragment extends Fragment {

    private StudioViewModel dashboardViewModel;
    private FragmentStudioBinding binding;
    ViewGroup viewGroup;
    RecyclerView recyclerView;
    private ArrayList<StudioData> studioDataArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(StudioViewModel.class);

        binding = FragmentStudioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewGroup=container;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onStart(){
        super.onStart();
        recyclerView =(RecyclerView) viewGroup.findViewById(R.id.studioRV);
        studioDataArrayList = new ArrayList<>();
        ArrayList<Post> post1 = new ArrayList<>(), post2 = new ArrayList<>();
        Post gauravPandey = new Post(R.mipmap.frame5);
        Post gauravPandeyorginal = new Post(R.mipmap.frame5original);
        Post Tejeswar = new Post(R.mipmap.frame1);
        Post Tejeswaroriginal = new Post(R.mipmap.frame1original);
        post1.add(gauravPandeyorginal);
        post1.add(gauravPandey);
        studioDataArrayList.add(new StudioData("The Happiest time of year is almost here and my wardrobe is all set with great ethnic fits for me!", "Gaurav Pandey", post1));
        post2.add(Tejeswaroriginal);
        post2.add(Tejeswar);
        studioDataArrayList.add(new StudioData("Trying to feel one with the nature in this Black & Olivegreen and Printed straight kurta from Anouk", "Tejeshwar Sandhoo", post2));
        StudioAdapter adapter=new StudioAdapter(studioDataArrayList,viewGroup.getContext());
//
        // setting grid layout manager to implement grid view.
        // in this method '1' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager( viewGroup.getContext(),1);
//
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}