package com.lik.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText UserNameEditText, PhoneEditText, PasswordEditText, ConfirmPasswordEditText;
    Button SignIn, SignUp;
    LoginDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserNameEditText = (EditText) findViewById(R.id.editTextName);
        PhoneEditText = (EditText) findViewById(R.id.editTextEmailAddress);
        PasswordEditText = (EditText) findViewById(R.id.editTextPassword);
        ConfirmPasswordEditText = (EditText) findViewById(R.id.editTextConfirmPassword);

        SignUp = (Button) findViewById(R.id.btn_save);

        DB = new LoginDBHelper(this);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = UserNameEditText.getText().toString();
                String email = PhoneEditText.getText().toString();
                String password = PasswordEditText.getText().toString();
                String confirmpassword = ConfirmPasswordEditText.getText().toString();

                if (username.equals("") || email.equals("") || password.equals("") || confirmpassword.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (password.equals(confirmpassword)) {
                        Boolean checkemail = DB.check_email(email);
                        if (checkemail == false) {
                            Boolean insert = DB.insertData(email, password);
                            if (insert == true) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Already Exist! Please Sign In", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}