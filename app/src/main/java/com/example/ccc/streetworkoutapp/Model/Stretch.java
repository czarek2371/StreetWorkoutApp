package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 02.04.2018.
 */

public class Stretch {
    private String Image, Name;

    public Stretch() {
    }

    public Stretch(String image, String name) {
        Image = image;
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
