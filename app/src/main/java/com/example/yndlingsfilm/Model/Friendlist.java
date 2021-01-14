package com.example.yndlingsfilm.Model;

public class Friendlist {

    private int imageResource;
    private String friendListName;
    private int friendListReviews;

    public Friendlist(int imageResource, String friendListName, int friendListReviews) {
        this.imageResource = imageResource;
        this.friendListName = friendListName;
        this.friendListReviews = friendListReviews;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getFriendListName() {
        return friendListName;
    }

    public int getFriendListReviews() {
        return friendListReviews;
    }
}
