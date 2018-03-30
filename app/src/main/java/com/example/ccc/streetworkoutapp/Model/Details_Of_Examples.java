package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 30.03.2018.
 */

public class Details_Of_Examples {
    private String ExampleId, Image, Name;

    public Details_Of_Examples() {
    }

    public Details_Of_Examples(String exampleId, String image, String name) {
        ExampleId = exampleId;
        Image = image;
        Name = name;
    }

    public String getExampleId() {
        return ExampleId;
    }

    public void setExampleId(String exampleId) {
        ExampleId = exampleId;
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
