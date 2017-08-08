package com.rohantaneja.loginsignup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rohantaneja.loginsignup.database.DBOpenHelper;
import com.rohantaneja.loginsignup.R;
import com.rohantaneja.loginsignup.model.UserModel;

public class SignUpActivity extends AppCompatActivity {

    DBOpenHelper db;
    EditText emailEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        db = new DBOpenHelper(this);

        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
    }

    public void submitClicked(View view) {

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (isValidData(email, password, confirmPassword)) {

            db.addUser(new UserModel(email, password));

            Toast.makeText(this, "Signup successful. User: " + emailEditText.getText().toString()
                    , Toast.LENGTH_SHORT).show();

            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Sign up unsuccessful." + emailEditText.getText().toString()
                    , Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidData(String email, String password, String confirmPassword) {

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Cannot be empty");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Cannot be empty");
            return false;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Cannot be empty");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }

        return true;
    }
}
