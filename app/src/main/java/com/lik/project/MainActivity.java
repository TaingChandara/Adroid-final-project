package com.lik.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText UserNameEditText, PhoneEditText, PasswordEditText, ConfirmPasswordEditText;
    Button SignIn, SignUp;
    LoginDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnsignup = (Button) findViewById(R.id.btn_sign_up);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });

        Button btnsignin = (Button) findViewById(R.id.btn_login);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                startActivityForResult(intent,1);
            }
        });

        PhoneEditText = (EditText) findViewById(R.id.editTextLoginEmailAddress);
        PasswordEditText = (EditText) findViewById(R.id.editTextLoginPassword);

        SignIn = (Button) findViewById(R.id.btn_login);

        DB = new LoginDBHelper(this);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = PhoneEditText.getText().toString();
                String password = PasswordEditText.getText().toString();

                if(phone.equals("") || password.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean check_email_password = DB.check_email_password(phone, password);
                    if (check_email_password==true){
                        Toast.makeText(MainActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}