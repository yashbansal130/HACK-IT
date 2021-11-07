package com.example.hack_it;


import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {


    private int image;

    public Post(int image) {
        this.image = image;
    }

    public Post(Post post) {
        this.image = post.image;
    }


    protected Post(Parcel in) {
        image = in.readInt();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
    }
}