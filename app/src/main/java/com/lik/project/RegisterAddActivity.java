package com.lik.project;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegisterAddActivity extends AppCompatActivity {

    EditText name_input, email_input, current_pass_input;
    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);
        current_pass_input = findViewById(R.id.current_pass_input);


        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterDBHelper myDB = new RegisterDBHelper(RegisterAddActivity.this);
                myDB.addBook(
                        name_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        current_pass_input.getText().toString().trim()
                );
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
