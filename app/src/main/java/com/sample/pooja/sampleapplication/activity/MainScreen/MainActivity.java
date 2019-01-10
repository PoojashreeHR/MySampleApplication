package com.sample.pooja.sampleapplication.activity.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sample.pooja.sampleapplication.R;
import com.sample.pooja.sampleapplication.activity.ItemList.ItemListActivity;
import com.sample.pooja.sampleapplication.database.AppDatabase;
import com.sample.pooja.sampleapplication.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sample.pooja.sampleapplication.utils.AppUtils.checkNetworkConnection;
import static com.sample.pooja.sampleapplication.utils.AppUtils.isEmailValid;

public class MainActivity extends AppCompatActivity implements MainActivityContract.MainActivityView {

    @BindView(R.id.et_email)
    EditText email;
    MainActivityPresenterImpl mainActivityPresenter;
    AppDatabase sampleDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenterImpl(this, this);
        sampleDatabase = AppDatabase.getInstance(this);
        if(!checkNetworkConnection(this)){
            updateUI();
        }
    }

    @OnClick(R.id.btn_submit)
    public void submit(View view) {
        // TODO submit data to server...
        if(checkNetworkConnection(this)) {
            if(validateCredential()) {
                AppUtils.showLoadingProgress(this);
                mainActivityPresenter.callItemListApi(email.getText().toString());
            }
        }else {
            updateUI();
        }
    }

    @Override
    public void showResponse(String responseString) {
        Toast.makeText(this, "" + responseString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI() {
        startActivity(new Intent(this, ItemListActivity.class));
        finish();
    }

    public boolean validateCredential(){
        boolean valid = true;
        if(email.getText().toString().isEmpty()){
            email.setError("Enter email");
            valid = false;
        }else if(!isEmailValid(email.getText().toString())){
            email.setError("Invalid Email!!");
            valid = false;
        }

        return valid;
    }

}
