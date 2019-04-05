package com.angel266489.fortuneteller;

public class ListData {

private String wish;
private int mIconEmail;
private int mStarIcon;

    public ListData(String wish, int mIconEmail, int mStarIcon) {
        this.wish = wish;
        this.mIconEmail = mIconEmail;
        this.mStarIcon = mStarIcon;
    }

    public String getWish() {
        return wish;
    }

    public int getmIconEmail() {
        return mIconEmail;
    }

    public int getmStarIcon() {
        return mStarIcon;
    }
}
