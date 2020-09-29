package com.example.yndlingsfilm.NavigationBar.Adapters;

import java.util.ArrayList;

/*
Denne klasse indeholder de de ting, som hver del af recyclerviewet skal indeholde, dvs. de ting der står på de forskellige lister af recyclerviews.
 */

public class ModelVertical {
    String titel;
    ArrayList<ModelHorizontal> arrayList;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public ArrayList<ModelHorizontal> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ModelHorizontal> arrayList) {
        this.arrayList = arrayList;
    }
}
