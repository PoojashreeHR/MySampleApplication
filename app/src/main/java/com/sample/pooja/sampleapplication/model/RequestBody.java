package com.sample.pooja.sampleapplication.model;

import com.google.gson.annotations.SerializedName;

public class RequestBody {
    @SerializedName("emailId")
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
