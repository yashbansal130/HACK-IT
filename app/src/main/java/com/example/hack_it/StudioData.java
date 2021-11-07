package com.example.hack_it;

import android.widget.ImageView;

import java.util.ArrayList;

public class StudioData {
    private String name;
    private String caption;
    ArrayList<Post> posts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCaption(){return caption; }

    public void setCaption(String caption){ this.caption=caption; }

    public void setPosts(ArrayList<Post> posts){
        this.posts=posts;
    }
    public ArrayList<Post> getPosts(){
        return posts;
    }
    public StudioData(String caption, String name, ArrayList<Post> posts) {
        this.caption = caption;
        this.name = name;
        this.posts = posts;
    }
}
