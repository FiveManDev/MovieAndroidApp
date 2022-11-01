package com.example.movieandroidapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.user.LoginContract;
import com.example.movieandroidapp.model.PayLoadToken;
import com.example.movieandroidapp.model.TokenModel;
import com.example.movieandroidapp.presenter.user.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private TextView btn_forgot,signup_link, userName, password, error_message;
    private Button btnLogin;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        btnLogin = findViewById(R.id.btnLogin);
        btn_forgot = findViewById(R.id.btn_forgot_password);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        progress = new ProgressDialog(this);
        signup_link = findViewById(R.id.signup_link);

        handleForgot();
        handleSignUp();
        handleLogin();
    }
    private void handleForgot(){
        btn_forgot.setOnClickListener(t->{
            startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
        });
    }
    private void handleSignUp(){
        signup_link.setOnClickListener(t -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
    private void handleLogin(){
        btnLogin.setOnClickListener(t -> {
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false);
            progress.show();
            LoginPresenter loginPresenter = new LoginPresenter(this);
            loginPresenter.requestLoginToServer(userName.getText().toString(), password.getText().toString());
        });
    }

    @Override
    public void onResponseSuccess(TokenModel tokenModel) {
        progress.dismiss();
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
        progress.dismiss();
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