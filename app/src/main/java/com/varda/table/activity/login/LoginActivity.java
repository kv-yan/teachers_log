package com.varda.table.activity.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.varda.table.activity.main.MainActivity;
import com.varda.table.databinding.ActivityLoginBinding;
import com.varda.table.helper.auth.AuthHelper;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthHelper helper = new AuthHelper(getApplicationContext());


        Intent mainFlow = new Intent(getApplicationContext(), MainActivity.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (helper.getAuthStatus()) {
            startActivity(mainFlow);
        }
        String info = "Info\nEmail '" + helper.getEmail() + "'\nPass '" + helper.getPassword() + "'";

        binding.btnLogin.setOnClickListener(view -> {
            helper.setAuthStatus(true);

            helper.setEmail(binding.textEmail.getText().toString());
            helper.setPassword(binding.textPass.getText().toString());
            startActivity(mainFlow);
        });
        binding.status.setText(info);
    }
}