package com.example.food_app.fragment.Register_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food_app.R;

public class PasswordActivity extends AppCompatActivity {
    EditText username;
    Button reset;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        username = findViewById(R.id.username_reset);
        reset = findViewById(R.id.btnreset);
        DB = new DBHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                Boolean checkUser = DB.checkUsername(user);
                if(checkUser == true){
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }else {
                    Toast.makeText(PasswordActivity.this, "User does not exists", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}