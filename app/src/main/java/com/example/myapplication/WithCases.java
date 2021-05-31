package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WithCases extends AppCompatActivity implements View.OnClickListener  {
    private Toolbar mToolbar;


    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_cases);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        btn9 = findViewById(R.id.button9);
        btn9.setOnClickListener(this);

        btn10 = findViewById(R.id.button10);
        btn10.setOnClickListener(this);

        btn11 = findViewById(R.id.button11);
        btn11.setOnClickListener(this);

        btn12 = findViewById(R.id.button12);
        btn12.setOnClickListener(this);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button9:

                Intent intent = new Intent(this, WithCases2.class);
                startActivity(intent);
                break;

            case R.id.button12:
                Intent intent2 = new Intent(this, AdminPanel3.class);
                startActivity(intent2);
                break;

            case R.id.button10:

                Intent intent3 = new Intent(this, WithCases2_1.class);
                startActivity(intent3);
                break;

            case R.id.button11:

                Intent intent4 = new Intent(this, Posts3.class);
                startActivity(intent4);
                break;

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.changeGender:
                Intent intent100 = new Intent(this, MainActivity.class);
                startActivity(intent100);
                break;

        }

        return super.onOptionsItemSelected(item);
    }



}