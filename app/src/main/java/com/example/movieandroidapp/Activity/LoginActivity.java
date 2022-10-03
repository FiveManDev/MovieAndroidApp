package com.example.movieandroidapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.contract.user.LoginContract;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.presenter.user.LoginPresenter;

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


    @Override
    public void onResponseSuccess(TokenModel tokenModel) {
        DataLocalManager.setAccessToken(tokenModel.getAccessToken());
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onResponseFailure(String message) {
        error_message = findViewById(R.id.error_message);
        error_message.setText(message);
        error_message.setVisibility(View.VISIBLE);
    }
}