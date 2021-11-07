package com.example.hack_it;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ViewPagerItemFragment extends Fragment {

    // widgets
    private ImageView mImage;

    // vars
    private Post mPost;

    public static ViewPagerItemFragment getInstance(Post post){
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();

        if(post != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("post", post);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mPost = getArguments().getParcelable("post");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImage = view.findViewById(R.id.item_image_viewpager);

        init();
    }

    private void init(){
        if(mPost != null){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mPost.getImage())
                    .into(mImage);

        }
    }
}