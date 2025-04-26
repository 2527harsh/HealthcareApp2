package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUserName, edPassword, edEmail, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUserName = findViewById(R.id.editTextAppFullName);
        edPassword = findViewById(R.id.editTextTextPassword);
        edConfirm = findViewById(R.id.editTextAppFees);
        edEmail = findViewById(R.id.editTextAppAddress);
        btn = findViewById(R.id.buttonBookApointment);
        tv = findViewById(R.id.tvNewUser);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUserName.getText().toString();
                String password = edPassword.getText().toString();
                String email = edEmail.getText().toString();
                Database db= new Database(getApplicationContext(),"healthcare",null,1);
                String confirm = edConfirm.getText().toString();
                if (username.length() == 0 || password.length() == 0 || email.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fill all the Details", Toast.LENGTH_LONG).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if(isValid(password)) {
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Password and confirm password didn't match", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public static boolean isValid(String passwordadhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordadhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordadhere.length(); p++) {
                if (Character.isLetter(passwordadhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordadhere.length(); r++) {
                if (Character.isDigit(passwordadhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordadhere.length(); s++) {
                char c = passwordadhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1) {
                return true;

            }
        }
        return false;
    }
}
