package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 29.03.2018.
 */

public class Number {
    private String Image, Name, numberId;

    public Number() {
    }

    public Number(String image, String name, String numberId) {
        Image = image;
        Name = name;
        this.numberId = numberId;
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

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }
}
