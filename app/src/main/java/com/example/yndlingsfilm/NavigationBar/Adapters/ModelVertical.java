package com.example.yndlingsfilm.NavigationBar.Adapters;

import java.util.ArrayList;

/*
Denne klasse indeholder de de ting, som hver del af recyclerviewet skal indeholde, dvs. de ting der står på de forskellige lister af recyclerviews.
 */

public class ModelVertical {
    String genre;
    ArrayList<ModelHorizontal> arrayList;

    public ModelVertical(String genre, ArrayList<ModelHorizontal> arrayList) {
        this.genre = genre;
        this.arrayList = arrayList;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<ModelHorizontal> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ModelHorizontal> arrayList) {
        this.arrayList = arrayList;
    }
}
