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

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.name_regis);
        password = (EditText) findViewById(R.id.password_regis);

        repassword = (EditText) findViewById(R.id.repass_regis);

        signin = (Button) findViewById(R.id.btn_login_reg);
        signup = (Button) findViewById(R.id.btn_register);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please fill all  the fields", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert == true){
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
