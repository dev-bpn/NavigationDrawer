package com.biotuandroidAplica.biologytu.model;

/**
 * Created by Dell on 4/2/2016.
 */
public class NavDrawerModel {

    private int icon;
    private String title;

    public NavDrawerModel(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
