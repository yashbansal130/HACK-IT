package com.example.hack_it;

public class RecyclerData {

    private String name;
    private String id;
    private String imageurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() { return imageurl; }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId(){return id; }

    public void setId(String id){ this.id=id; }

    public RecyclerData(String id, String name, String imageurl) {
        this.id = id;
        this.name = name;
        this.imageurl = imageurl;
    }


}
