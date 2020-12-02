package com.gokisoft.c1812l;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText txtFullname, txtUsername, txtPassword, txtConfirmPwd;
    Button btnBack, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtFullname = findViewById(R.id.ar_txt_fullname);
        txtUsername = findViewById(R.id.ar_txt_username);
        txtPassword = findViewById(R.id.ar_txt_password);
        txtConfirmPwd = findViewById(R.id.ar_txt_confirm_password);
        btnBack = findViewById(R.id.ar_btn_back);
        btnSave = findViewById(R.id.ar_btn_save);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = txtFullname.getText().toString();
                String username = txtUsername.getText().toString();
                String pwd = txtPassword.getText().toString();
                String confirmPwd = txtConfirmPwd.getText().toString();

                if(username.isEmpty() || !pwd.equals(confirmPwd)) {
                    Toast.makeText(RegisterActivity.this, "Check inputs again!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("fullname", fullname);
                intent.putExtra("username", username);
                intent.putExtra("pwd", pwd);
                intent.putExtra("confirmPwd", confirmPwd);

                setResult(LoginActivity.REQUEST_CODE_REGISTER, intent);
                finish();
            }
        });
    }
}
