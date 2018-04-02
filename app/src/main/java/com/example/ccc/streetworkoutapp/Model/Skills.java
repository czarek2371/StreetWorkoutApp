package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 02.04.2018.
 */

public class Skills {
    private String Image, Name;

    public Skills() {
    }

    public Skills(String image, String name, String skillId) {
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
