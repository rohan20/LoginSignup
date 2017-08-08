package com.rohantaneja.loginsignup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rohantaneja.loginsignup.database.DBOpenHelper;
import com.rohantaneja.loginsignup.R;
import com.rohantaneja.loginsignup.model.UserModel;

public class LoginActivity extends AppCompatActivity {

    DBOpenHelper dbOpenHelper;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        dbOpenHelper = new DBOpenHelper(this);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
    }


    public void signUpClicked(View view) {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    public void loginClicked(View view) {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        UserModel user = new UserModel(email, password);

        if (dbOpenHelper.checkIfUserExists(user)) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }


    }
}
