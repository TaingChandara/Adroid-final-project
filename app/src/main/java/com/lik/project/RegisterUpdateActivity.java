package com.lik.project;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegisterUpdateActivity extends AppCompatActivity {

    EditText name_input, email_input, current_pass_input, new_pass_input, confirm_new_pass_input;
    Button update_button, delete_button;

    String Id, Name, Email, Current_Pass, New_Pass, Confirm_New_Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_update);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name_input = findViewById(R.id.name_input1);
        email_input = findViewById(R.id.email_input1);
        current_pass_input = findViewById(R.id.current_pass_input1);
        new_pass_input = findViewById(R.id.new_pass_input1);
        confirm_new_pass_input = findViewById(R.id.confirm_new_pass_input1);

        update_button = findViewById(R.id.update_button1);
        delete_button = findViewById(R.id.delete_button1);
        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(Name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                RegisterDBHelper myDB = new RegisterDBHelper(RegisterUpdateActivity.this);
                Name = name_input.getText().toString().trim();
                Email = email_input.getText().toString().trim();
                Current_Pass = current_pass_input.getText().toString().trim();
                New_Pass = new_pass_input.getText().toString().trim();
                Confirm_New_Pass = confirm_new_pass_input.getText().toString().trim();

                myDB.updateData(Id, Name, Email,Current_Pass, New_Pass, Confirm_New_Pass);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("Id") && getIntent().hasExtra("Name") &&
           getIntent().hasExtra("Email") && getIntent().hasExtra("Current_Pass") &&
           getIntent().hasExtra("New_Pass") && getIntent().hasExtra("Confirm_New_Pass")){
            //Getting Data from Intent
            Id = getIntent().getStringExtra("Id");
            Name = getIntent().getStringExtra("Name");
            Email = getIntent().getStringExtra("Email");
            Current_Pass = getIntent().getStringExtra("Current_Pass");
            New_Pass = getIntent().getStringExtra("New_Pass");
            Confirm_New_Pass = getIntent().getStringExtra("Confirm_New_Pass");

            //Setting Intent Data
            name_input.setText(Name);
            email_input.setText(Email);
            current_pass_input.setText(Current_Pass);
            new_pass_input.setText(New_Pass);
            confirm_new_pass_input.setText(Confirm_New_Pass);
            Log.d("stev", Name+" "+Email+" "+Current_Pass+" "+New_Pass+" "+Confirm_New_Pass);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Name + " ?");
        builder.setMessage("Are you sure you want to delete " + Name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RegisterDBHelper myDB = new RegisterDBHelper(RegisterUpdateActivity.this);
                myDB.deleteOneRow(Id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
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
