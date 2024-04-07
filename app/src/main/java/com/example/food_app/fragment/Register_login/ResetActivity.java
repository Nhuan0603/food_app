package com.example.food_app.fragment.Register_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food_app.R;

public class ResetActivity extends AppCompatActivity {
    TextView username;
    EditText pass,repass;
    Button confirm;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        username = findViewById(R.id.username_reset_text);
        pass = findViewById(R.id.password_reset);
        repass = findViewById(R.id.repassword_reset);
        confirm = findViewById(R.id.btnconfirm);
        DB = new DBHelper(this);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if (password.equals(repassword)) {


                    Boolean checkPasswordUpdate = DB.updatePassword(user, password);
                    if (checkPasswordUpdate == true) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password updated Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(ResetActivity.this, "Password Not updated ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ResetActivity.this, "Password Not Matching ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
