package com.example.yndlingsfilm.Model;

public class Friendlist {

    private String imageResource;
    private String friendListName;
    private int friendListReviews;

    public Friendlist(String imageResource, String friendListName, int friendListReviews) {
        this.imageResource = imageResource;
        this.friendListName = friendListName;
        this.friendListReviews = friendListReviews;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getFriendListName() {
        return friendListName;
    }

    public int getFriendListReviews() {
        return friendListReviews;
    }
}
