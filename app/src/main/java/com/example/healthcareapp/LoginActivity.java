package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName= findViewById(R.id.editTextAppFullName);
        edPassword=findViewById(R.id.editTextTextPassword);
        btn=findViewById(R.id.btnLogin);
        tv=findViewById(R.id.tvNewUser);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                String username= edUserName.getText().toString();
//                String password=edPassword.getText().toString();
//                Database db= new Database(getApplicationContext(),"healthcare",null,1);
//
//                if(username.length()==0 || password.length()==0){
//                    Toast.makeText(getApplicationContext(),"Fill all the Details",Toast.LENGTH_LONG).show();
//                }
//                else{
//                    if(db.login(username,password)==1) {
//                        Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences= getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor=sharedPreferences.edit();
//                        editor.putString("username",username);
//                        editor.apply(); // Changed from editor.commit() to editor.apply()
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    } else {
//                        // Handle unsuccessful login
//                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
//                    }
//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//
//
//                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }

        });
    }
}
