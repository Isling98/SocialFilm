package com.example.yndlingsfilm.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelationshipResponse {

    @SerializedName("userone_id")
    @Expose
    private int userOneId;

    @SerializedName("usertwo_id")
    @Expose
    private int userTwoId;

    public RelationshipResponse(int userOneId, int userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    public int getUserOneId() {
        return userOneId;
    }

    public int getUserTwoId() {
        return userTwoId;
    }

}
