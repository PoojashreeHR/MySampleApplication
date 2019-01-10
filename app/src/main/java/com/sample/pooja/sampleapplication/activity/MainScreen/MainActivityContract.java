package com.sample.pooja.sampleapplication.activity.MainScreen;

public interface MainActivityContract {

    interface MainActivityView{
        void showResponse(String responseString);
        void updateUI();
    }

    interface MainActivityPresenter{
        void callItemListApi(String email);
    }
}
