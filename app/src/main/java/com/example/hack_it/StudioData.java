package com.example.hack_it;

import android.widget.ImageView;

public class StudioData {
    private String name;
    private String caption;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCaption(){return caption; }

    public void setCaption(String caption){ this.caption=caption; }

    public StudioData(String caption, String name) {
        this.caption = caption;
        this.name = name;
    }
}
