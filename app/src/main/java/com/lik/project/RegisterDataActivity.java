package com.lik.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RegisterDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    RegisterDBHelper myDB;
    ArrayList<String> Id, Name, Email, Current_Pass, New_Pass, Confirm_New_Pass;

    RegisterCustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterDataActivity.this, RegisterAddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new RegisterDBHelper(RegisterDataActivity.this);
        Id = new ArrayList<>();
        Name = new ArrayList<>();
        Email = new ArrayList<>();
        Current_Pass = new ArrayList<>();
        New_Pass = new ArrayList<>();
        Confirm_New_Pass = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new RegisterCustomAdapter(RegisterDataActivity.this, this,
                Id, Name, Email, Current_Pass, New_Pass, Confirm_New_Pass);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RegisterDataActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                Id.add(cursor.getString(0));
                Name.add(cursor.getString(1));
                Email.add(cursor.getString(2));
                Current_Pass.add(cursor.getString(3));
                New_Pass.add(cursor.getString(4));
                Confirm_New_Pass.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(this, RegisterDataActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.refresh) {
            Intent intent = new Intent(this, RegisterDataActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RegisterDBHelper myDB = new RegisterDBHelper(RegisterDataActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(RegisterDataActivity.this, RegisterDataActivity.class);
                startActivity(intent);
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
}
