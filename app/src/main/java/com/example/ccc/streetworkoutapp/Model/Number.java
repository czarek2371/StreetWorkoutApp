package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 29.03.2018.
 */

public class Number {
    private String Image, Name, NumberId;

    public Number() {
    }

    public Number(String image, String name, String numberId, String description) {
        Image = image;
        Name = name;
        NumberId = numberId;
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
        return NumberId;
    }

    public void setNumberId(String numberId) {
        NumberId = numberId;
    }

}
