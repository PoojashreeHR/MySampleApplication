package com.sample.pooja.sampleapplication.api;

import com.sample.pooja.sampleapplication.model.ItemList;
import com.sample.pooja.sampleapplication.model.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/list")
    Call<ItemList> callItemList(@Body RequestBody requestBody);
}
