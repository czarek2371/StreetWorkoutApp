package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 01.03.2018.
 */

public class Training_Plans {
    private String Image;
    private String Name;

    public Training_Plans() {
    }

    public Training_Plans(String image, String name) {
        this.Image = image;
        this.Name = name;
    }

    public String getImage() { return Image; }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
