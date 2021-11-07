package com.example.hack_it;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.StudioViewHolder> {

    Context mcontext;
    ArrayList<StudioData> studioDataArrayList;
    Post[] posts;

    public StudioAdapter(ArrayList<StudioData> studioDataArrayList, Context mcontext, Post[] posts) {
        this.studioDataArrayList = studioDataArrayList;
        this.mcontext = mcontext;
        this.posts=posts;

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
        holder.studioCaption.setText(studioData.getCaption());
        ArrayList<Fragment> fragments = new ArrayList<>();
        for(Post post: posts){
            Log.i("hi", Integer.toString(post.getImage()));
            ViewPagerItemFragment fragment = ViewPagerItemFragment.getInstance(post);
            fragments.add(fragment);
        }
        StudioPagerAdapter pagerAdapter = new StudioPagerAdapter(((AppCompatActivity)mcontext).getSupportFragmentManager(), fragments);
        holder.mMyViewPager.setAdapter(pagerAdapter);
        holder.mTabLayout.setupWithViewPager(holder.mMyViewPager, true);
    }

    @Override
    public int getItemCount() {
        return studioDataArrayList.size();
    }
    // View Holder Class to handle Recycler View.
    public class StudioViewHolder extends RecyclerView.ViewHolder{

        private TextView studioName;
        private TextView studioCaption;
        private ViewPager mMyViewPager;
        private TabLayout mTabLayout;

        public StudioViewHolder(@NonNull View itemView) {
            super(itemView);
            studioName = itemView.findViewById(R.id.studio_name);
            studioCaption = itemView.findViewById(R.id.studio_caption);
            mTabLayout = itemView.findViewById(R.id.tab_layout);
            mMyViewPager = itemView.findViewById(R.id.studio_image);
        }
    }

}






