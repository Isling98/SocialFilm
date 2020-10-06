package com.example.yndlingsfilm.NavigationBar.Adapters;


/*
Denne klasse indeholder de de ting, som hver del af recyclerviewet skal indeholde, dvs. de ting der står på de forskellige cards.
 */

public class ModelHorizontal {

    String name;
    Integer imageUrl;

    public ModelHorizontal(String name, Integer imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
