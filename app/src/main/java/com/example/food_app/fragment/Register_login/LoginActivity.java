package com.example.food_app.fragment.Register_login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.MainActivity;
import com.example.food_app.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout textInputLayoutEmail;

    TextInputEditText username;
    Button btnlogin;
    TextView tvBackToRegister;
    TextInputLayout textInputLayout;
    TextInputEditText passwordLogin;

    DBHelper DB;
    TextView forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        forgot = findViewById(R.id.fogot);
        username = (TextInputEditText) findViewById(R.id.ed_email_login_content);
        passwordLogin = (TextInputEditText) findViewById(R.id.ed_password_login_content);
        btnlogin = (Button) findViewById(R.id.btn_login_login);
        tvBackToRegister = findViewById(R.id.tv_backRegister);
        tvBackToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = passwordLogin.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checlusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });

//        passwordLogin.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String password = s.toString();
//                if (password.length() >= 8) {
//                    Pattern pattern = Pattern.compile("[a-zA-z0-9]");
//                    Matcher matcher = pattern.matcher(password);
//                    boolean passmatch = matcher.find();
//                    if (passmatch) {
//                        textInputLayout.setHelperText("Your password is Strong!");
//                        textInputLayout.setError("");
//                    } else {
//                        textInputLayout.setError("Mix of letters or number");
//                    }
//                } else {
//                    textInputLayout.setHelperText("Password must 8 characters long!");
//                    textInputLayout.setError("");
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }}