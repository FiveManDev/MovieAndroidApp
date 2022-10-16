package com.example.movieandroidapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.contract.user.LoginContract;
import com.example.movieandroidapp.model.PayLoadToken;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.presenter.user.GetUserInformationPresenter;
import com.example.movieandroidapp.presenter.user.LoginPresenter;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private TextView signup_link, userName, password, error_message;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        LoginPresenter loginPresenter = new LoginPresenter(this);
        signup_link = findViewById(R.id.signup_link);
        signup_link.setOnClickListener(t -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        btnLogin.setOnClickListener(t -> {
            loginPresenter.requestLoginToServer(userName.getText().toString(), password.getText().toString());
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResponseSuccess(TokenModel tokenModel) {
        DataLocalManager.setAccessToken(tokenModel.getAccessToken());
        String[] decoded = Extension.decodeAccessToken(tokenModel.getAccessToken());
        PayLoadToken payload = Extension.GsonUtil().fromJson(decoded[1], PayLoadToken.class);
        DataLocalManager.setUserId(payload.getUserID());
        navigate(payload.getRole());
    }
    public void navigate(String role){
        if (checkRole(role)) {
            startActivity( new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, AdminActivity.class));
        }
    }

    @Override
    public void onResponseFailure(String message) {
        error_message = findViewById(R.id.error_message);
        error_message.setText(message);
        error_message.setVisibility(View.VISIBLE);
    }

    public boolean checkRole(String role) {
        if (role.equals("User")) {
            return true;
        } else if (role.equals("Admin")) {
            return false;
        }
        return false;
    }
}