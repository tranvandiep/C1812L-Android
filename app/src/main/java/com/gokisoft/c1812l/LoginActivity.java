package com.gokisoft.c1812l;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_REGISTER = 1;

    EditText txtUsername, txtPassword;
    Button btnRegister, btnLogin;

    String username = "", password = "", fullname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mapping fields
        txtUsername = findViewById(R.id.al_txt_username);
        txtPassword = findViewById(R.id.al_txt_password);
        btnRegister = findViewById(R.id.al_btn_register);
        btnLogin = findViewById(R.id.al_btn_login);

        //dang ky su kien khi click regsiter & login
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lam the de start activity tu activity nay
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this, "Check login", Toast.LENGTH_SHORT).show();
                String uname = txtUsername.getText().toString();
                String pwd = txtPassword.getText().toString();
                if(uname.equals(username) && pwd.equals(password)) {
                    Toast.makeText(LoginActivity.this, "Welcome " + fullname, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_REGISTER:
                //du lieu duoc tra ve tu RegisterActivity
                if(data != null) {
                    fullname = data.getStringExtra("fullname");
                    username = data.getStringExtra("username");
                    password = data.getStringExtra("pwd");
                }
                break;
        }
    }
}
