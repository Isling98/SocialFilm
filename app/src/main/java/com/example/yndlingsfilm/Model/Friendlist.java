package com.example.yndlingsfilm.Model;

public class Friendlist {

    private int imageResource;
    private String friendListName;
    private String friendListReviews;

    public Friendlist(int imageResource, String friendListName, String friendListReviews) {
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

    public String getFriendListReviews() {
        return friendListReviews;
    }
}
