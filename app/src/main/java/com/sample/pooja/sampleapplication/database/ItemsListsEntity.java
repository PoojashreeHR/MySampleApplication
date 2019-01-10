package com.sample.pooja.sampleapplication.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "suryaItems")
public class ItemsListsEntity {
   /* @ColumnInfo(autoGenerate = true)
    private int id;*/

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "fname")
    private String fname;

    @ColumnInfo(name = "lname")
    private String lname;

    @ColumnInfo(name = "imageurl")
    private String imageUrl;

    public ItemsListsEntity() {
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String content) {
        this.email = content;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
