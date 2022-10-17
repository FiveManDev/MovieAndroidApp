package com.example.movieandroidapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.user.ResetPasswordContract;
import com.example.movieandroidapp.presenter.user.ResetPasswordPresenter;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordContract.View {
    Button btn_reset_password;
    EditText reset_password_new,reset_password_confirm;
    String email;
    TextView reset_error_message,signin_link_forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        btn_reset_password = findViewById(R.id.btn_reset_password);
        reset_password_confirm = findViewById(R.id.reset_password_confirm);
        reset_password_new = findViewById(R.id.reset_password_new);
        reset_error_message = findViewById(R.id.reset_error_message);
        signin_link_forgot = findViewById(R.id.signin_link_forgot);

        email = getIntent().getExtras().getString("email");

        btn_reset_password.setOnClickListener(t->{
            ResetPasswordPresenter presenter = new ResetPasswordPresenter(this);
            presenter.confirmEmailToServer(reset_password_new.getText().toString(),reset_password_confirm.getText().toString(),email);
        });

        signin_link_forgot.setOnClickListener(t->{
            backToLogin();
        });
    }

    @Override
    public void onResponseSuccess() {
        reset_password_confirm.setText("");
        reset_password_new.setText("");
        Toast.makeText(this, "Your password has been changed", Toast.LENGTH_SHORT).show();
        backToLogin();
    }
    private void backToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponseFailure(String message) {
        reset_error_message.setVisibility(View.VISIBLE);
        reset_error_message.setText(message);
    }
}