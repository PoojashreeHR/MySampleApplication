package com.sample.pooja.sampleapplication.activity.MainScreen;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.sample.pooja.sampleapplication.api.ApiClient;
import com.sample.pooja.sampleapplication.api.ApiInterface;
import com.sample.pooja.sampleapplication.database.AppDatabase;
import com.sample.pooja.sampleapplication.database.ItemsDao;
import com.sample.pooja.sampleapplication.database.ItemsListsEntity;
import com.sample.pooja.sampleapplication.model.ItemList;
import com.sample.pooja.sampleapplication.model.RequestBody;
import com.sample.pooja.sampleapplication.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenterImpl implements MainActivityContract.MainActivityPresenter {
    Context mContext;
    MainActivityContract.MainActivityView mMainView;
    ItemsListsEntity itemsListsEntityTable;
    ItemsDao itemsDao;
    List<ItemsListsEntity> itemsListsEntity;

    MainActivityPresenterImpl(Context context,MainActivityContract.MainActivityView mainView){
       this.mContext = context;
       this.mMainView = mainView;
        itemsListsEntity = new ArrayList<>();
        itemsDao = AppDatabase.getInstance(context).getItems();
    }

    @Override
    public void callItemListApi(String email) {
        ApiInterface apiInterface = ApiClient.createService(mContext, ApiInterface.class);
        RequestBody requestBody = new RequestBody();
        requestBody.setEmailId(email);
        Call<ItemList> call = apiInterface.callItemList(requestBody);
        call.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                if(response.code() == 200) {
                    if (response.body().getItems().size() > 0) {
                        AppUtils.hideLoadingProgress();
                        for (int i = 0; i < response.body().getItems().size(); i++) {
                            itemsListsEntityTable = new ItemsListsEntity();

                            ItemList.Items items = response.body().getItems().get(i);
                            itemsListsEntityTable.setEmail(items.getEmailId());
                            itemsListsEntityTable.setFname(items.getFirstName());
                            itemsListsEntityTable.setLname(items.getLastName());
                            itemsListsEntityTable.setImageUrl(items.getImageUrl());

                            itemsListsEntity.add(itemsListsEntityTable);
                        }
                        //Using Async task to store data in the background
                        new DatabaseAsync().execute();
                        //mMainView.showResponse("List of items found");

                    } else {
                        AppUtils.hideLoadingProgress();
                        mMainView.showResponse("No items found");
                    }
                }else {
                    AppUtils.hideLoadingProgress();
                    mMainView.showResponse("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                AppUtils.hideLoadingProgress();
                mMainView.showResponse("Something went wrong");
            }
        });
    }


    private class DatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Perform pre-adding operation here.
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                itemsDao.insertListOfItems(itemsListsEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            itemsListsEntity.clear();
            mMainView.updateUI();
        }
    }
}
