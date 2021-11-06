package com.example.hack_it;

public class WishlistData {
    private String name;
    private String id;

    public String getName() {
        return name;
    }
    public String getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id){
        this.id=id;
    }

    public WishlistData(String name, String id) {
        this.name = name;
        this.id=id;
    }
}
