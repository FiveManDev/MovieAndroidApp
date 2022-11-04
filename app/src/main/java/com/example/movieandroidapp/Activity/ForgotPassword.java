package com.example.movieandroidapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.user.ForgotPasswordContract;
import com.example.movieandroidapp.presenter.user.ForgotPasswordPresenter;

public class ForgotPassword extends AppCompatActivity implements ForgotPasswordContract.View {
    EditText email_forgot;
    TextView error_message_forgot;
    Button btn_send_forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email_forgot = findViewById(R.id.email_forgot);
        btn_send_forgot = findViewById(R.id.btn_send_forgot);
        error_message_forgot = findViewById(R.id.error_message_forgot);

        btn_send_forgot.setOnClickListener(t -> {
            ForgotPasswordPresenter presenter = new ForgotPasswordPresenter(this);
            presenter.confirmEmailToServer(email_forgot.getText().toString().trim());
        });
    }

    @Override
    public void onResponseSuccess(String code) {
        error_message_forgot.setVisibility(View.GONE);
        error_message_forgot.setText("");
        bundleData(code);
    }

    @Override
    public void onResponseFailure(String message) {
        error_message_forgot.setVisibility(View.VISIBLE);
        error_message_forgot.setText(message);
    }

    private void bundleData(String code) {
        Intent intent = new Intent(this, ConfirmEmail.class);
        intent.putExtra("Action", "forgot");
        intent.putExtra("code", code);
        intent.putExtra("email", email_forgot.getText().toString());
        startActivity(intent);
    }
}