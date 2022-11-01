package com.example.movieandroidapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.user.ConfirmEmailContract;
import com.example.movieandroidapp.presenter.user.ConfirmEmailPresenter;

public class RegisterActivity extends AppCompatActivity implements ConfirmEmailContract.View {
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

        ConfirmEmailPresenter confirmEmailPresenter = new ConfirmEmailPresenter(this);

        userName =  findViewById(R.id.username_register);
        password =  findViewById(R.id.password_register);
        email =  findViewById(R.id.email_register);

        register_error_message = findViewById(R.id.register_error_message);

        btnRegister.setOnClickListener(t -> {
            confirmEmailPresenter.confirmEmailToServer(
                    userName.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString());
        });
        signin_link.setOnClickListener(t -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }

    @Override
    public void onResponseSuccess(String code) {
        register_error_message.setVisibility(View.GONE);
        bundleData(userName.getText().toString(), email.getText().toString(), password.getText().toString(),code);

        userName.setText("");
        password.setText("");;
        email.setText("");
    }

    private void bundleData(String username,String email, String password, String code){
        Intent intent = new Intent(this,ConfirmEmail.class);
        intent.putExtra("Action","register");
        intent.putExtra("userName",username);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("code",code);
        startActivity(intent);
    }

    @Override
    public void onResponseFailure(String message) {
        register_error_message.setVisibility(View.VISIBLE);
        register_error_message.setText(message);
    }
}