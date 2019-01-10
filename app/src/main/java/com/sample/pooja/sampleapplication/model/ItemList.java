package com.sample.pooja.sampleapplication.model;

import android.content.ClipData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemList {
    @SerializedName("items")
    private List<Items> items = null;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public class Items{
        @SerializedName("emailId")
        private String emailId;
        @SerializedName("lastName")
        private String lastName;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("firstName")
        private String firstName;

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }


}
