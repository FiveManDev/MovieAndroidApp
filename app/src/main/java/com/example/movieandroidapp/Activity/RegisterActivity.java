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
import com.example.movieandroidapp.contract.user.CreateAccountContract;
import com.example.movieandroidapp.presenter.user.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements CreateAccountContract.View {
    private TextView signin_link;
    private EditText userName,email,password;
    private Button btnRegister;
    private TextView register_error_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signin_link = findViewById(R.id.signin_link);
        btnRegister = findViewById(R.id.btnRegister);
        RegisterPresenter registerPresenter = new RegisterPresenter(this);

        userName =  findViewById(R.id.username_register);
        password =  findViewById(R.id.password_register);
        email =  findViewById(R.id.email_register);

        register_error_message = findViewById(R.id.register_error_message);

        btnRegister.setOnClickListener(t -> {
            registerPresenter.requestCreateUserToServer(
                    userName.getText().toString(), email.getText().toString(), password.getText().toString());
        });
        signin_link.setOnClickListener(t -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    @Override
    public void onResponseSuccess() {
        register_error_message.setVisibility(View.GONE);
        Toast.makeText(this, "Create user successfully!", Toast.LENGTH_SHORT).show();
        userName.setText("");
        password.setText("");;
        email.setText("");
    }

    @Override
    public void onResponseFailure(String message) {
        register_error_message.setVisibility(View.VISIBLE);
        register_error_message.setText(message);
    }
}