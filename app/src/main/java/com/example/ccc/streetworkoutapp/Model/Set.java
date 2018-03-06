package com.example.ccc.streetworkoutapp.Model;

/**
 * Created by ccc on 06.03.2018.
 */

public class Set {
    private String Description, Image, MenuId, Name;

    public Set() {
    }

    public Set(String description, String image, String menuId, String name) {
        Description = description;
        Image = image;
        MenuId = menuId;
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}