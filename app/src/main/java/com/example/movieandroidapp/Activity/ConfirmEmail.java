package com.example.movieandroidapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.user.CreateAccountContract;
import com.example.movieandroidapp.presenter.user.RegisterPresenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ConfirmEmail extends AppCompatActivity {

    String action, userName, email, password, code;
    EditText code_input;
    Button btn_confirm;
    TextView error_message_confirm;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);
        error_message_confirm = findViewById(R.id.error_message_confirm);

        code_input = findViewById(R.id.code_input);
        btn_confirm = findViewById(R.id.btn_confirm);

        email = getIntent().getExtras().getString("email");
        code = getIntent().getExtras().getString("code");
        action = getIntent().getExtras().getString("Action");

        progress = new ProgressDialog(this);

        if (action.equals("register")) {
            handleRegister();
        } else if (action.equals("forgot")) {
            handleForgotPassword();
        }
    }
    private void handleRegister(){
        userName = getIntent().getExtras().getString("userName");
        password = getIntent().getExtras().getString("password");

        CreateAccountContract.View view = new CreateAccountContract.View() {
            @Override
            public void onResponseSuccess() {
                progress.dismiss();
                error_message_confirm.setVisibility(View.GONE);
                Toast.makeText(ConfirmEmail.this, "Create account successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ConfirmEmail.this, LoginActivity.class));
            }
            @Override
            public void onResponseFailure(String message) {
                progress.dismiss();
                error_message_confirm.setVisibility(View.VISIBLE);
                error_message_confirm.setText(message);
            }
        };

        RegisterPresenter registerPresenter = new RegisterPresenter(view);

        btn_confirm.setOnClickListener(t -> {
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false);
            progress.show();

            registerPresenter.requestCreateUserToServer(userName, email, password, code_input.getText().toString(), code);
        });
    }

    private void handleForgotPassword(){
        btn_confirm.setOnClickListener(t ->{
            if(code.equals(code_input.getText().toString())){
                Intent intent = new Intent(this, ResetPasswordActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
            else{
                error_message_confirm.setVisibility(View.VISIBLE);
                error_message_confirm.setText("Incorrect code");
            }
        });
    }
}