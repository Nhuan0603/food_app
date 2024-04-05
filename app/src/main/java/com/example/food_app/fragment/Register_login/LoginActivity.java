package com.example.food_app.fragment.Register_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app.MainActivity;
import com.example.food_app.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.ed_email_login);
        password = (EditText) findViewById(R.id.ed_password_login);
        btnlogin = (Button) findViewById(R.id.btn_login_login);
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checlusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}