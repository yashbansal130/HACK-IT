package com.example.hack_it;

import android.widget.ImageView;

public class StudioData {
    private String name;
    private String caption;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() { return image; }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCaption(){return caption; }

    public void setCaption(String caption){ this.caption=caption; }

    public StudioData(String caption, String name, int image) {
        this.caption = caption;
        this.name = name;
        this.image = image;
    }
}
