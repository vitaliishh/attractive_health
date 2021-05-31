package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminPanel4_2 extends AppCompatActivity implements View.OnClickListener{


    DBHelperStatic dbHelper;

    private Toolbar mToolbar;

    private Button allPosts;
    private Button dellBtn;
    private Button addBtn;
    private Button clearAllPosts;

    private EditText etType;
    private EditText etOpt1;
    private EditText etOpt2;
    private EditText etOpt3;
    private EditText etOpt4;
    private EditText etId;
    private EditText etGender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel4_2);


        dbHelper = new DBHelperStatic(this);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        allPosts = findViewById(R.id.backBtn);
        allPosts.setOnClickListener(this);

        dellBtn = findViewById(R.id.dellBtn);
        dellBtn.setOnClickListener(this);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        clearAllPosts = findViewById(R.id.clearAllPosts);
        clearAllPosts.setOnClickListener(this);

        etType = (EditText) findViewById(R.id.type);
        etOpt1 = (EditText) findViewById(R.id.opt1);
        etOpt2 = (EditText) findViewById(R.id.opt2);
        etOpt3 = (EditText) findViewById(R.id.opt3);
        etOpt4 = (EditText) findViewById(R.id.opt4);
        etGender = (EditText) findViewById(R.id.gender);

        etId = (EditText) findViewById(R.id.idInp);

    }


    @Override
    public void onClick(View v) {

        String type = etType.getText().toString();
        String opt1 = etOpt1.getText().toString();
        String opt2 = etOpt2.getText().toString();
        String opt3 = etOpt3.getText().toString();
        String opt4 = etOpt4.getText().toString();
        String gender = etGender.getText().toString();

        String id = etId.getText().toString();



        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.backBtn:

                Intent intent = new Intent(this, AdminPanel5AllPosts_2.class);
                startActivity(intent);




                Cursor cursor = database.query(DBHelperStatic.TABLE_TRAINING,null,null,null,null,null,null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelperStatic.KEY_ID_2);
                    int typeIndex = cursor.getColumnIndex(DBHelperStatic.KEY_TYPE_2);
                    int opt1Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT1_2);
                    int opt2Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT2_2);
                    int opt3Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT3_2);
                    int opt4Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT4_2);
                    int genderIndex = cursor.getColumnIndex(DBHelperStatic.KEY_GENDER_2);

                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", type = " + cursor.getString(typeIndex) +
                                ", opt1 = " + cursor.getString(opt1Index) +
                                ", opt2 = " + cursor.getString(opt2Index) +
                                ", opt3 = " + cursor.getString(opt3Index) +
                                ", opt4 = " + cursor.getString(opt4Index) +
                                ", gender = " + cursor.getString(genderIndex));

                    }while (cursor.moveToNext());
                }else {
                    Log.d("mLog", "0 rows");
                }

                cursor.close();

                break;




            case R.id.dellBtn:

                if (id.equalsIgnoreCase("")){
                    break;
                }

                database.delete(DBHelperStatic.TABLE_TRAINING, DBHelperStatic.KEY_ID_2 + "= " + id, null);


                //search filter
//                Cursor cursor2 =  database.query(DBHelper.TABLE_DIET, null, "type = ? AND gender = ?", new String[]{"dry", "man"},null,null,null);
//
//
//
//                if (cursor2.moveToFirst()) {
//                    int idIndex = cursor2.getColumnIndex(DBHelper.KEY_ID)
//                    int typeIndex = cursor2.getColumnIndex(DBHelper.KEY_TYPE);
//                    int opt1Index = cursor2.getColumnIndex(DBHelper.KEY_OPT1);
//                    int opt2Index = cursor2.getColumnIndex(DBHelper.KEY_OPT2);
//                    int opt3Index = cursor2.getColumnIndex(DBHelper.KEY_OPT3);
//                    int opt4Index = cursor2.getColumnIndex(DBHelper.KEY_OPT4);
//                    int opt5Index = cursor2.getColumnIndex(DBHelper.KEY_OPT5);
//                    int opt6Index = cursor2.getColumnIndex(DBHelper.KEY_OPT6);
//                    int genderIndex = cursor2.getColumnIndex(DBHelper.KEY_GENDER);
//
//                    do {
//                        Log.d("mLog", "ID = " + cursor2.getInt(idIndex) +
//                                ", type = " + cursor2.getString(typeIndex) +
//                                ", opt1 = " + cursor2.getString(opt1Index) +
//                                ", opt2 = " + cursor2.getString(opt2Index) +
//                                ", opt3 = " + cursor2.getString(opt3Index) +
//                                ", opt4 = " + cursor2.getString(opt4Index) +
//                                ", opt5 = " + cursor2.getString(opt5Index) +
//                                ", opt6 = " + cursor2.getString(opt6Index) +
//                                ", gender = " + cursor2.getString(genderIndex));
//
//                    }while (cursor2.moveToNext());
//                }else {
//                    Log.d("mLog", "0 rows");
//                }
//
//                cursor2.close();



                break;


            case R.id.addBtn:


                contentValues.put(DBHelperStatic.KEY_TYPE_2, type);
                contentValues.put(DBHelperStatic.KEY_OPT1_2, opt1);
                contentValues.put(DBHelperStatic.KEY_OPT2_2, opt2);
                contentValues.put(DBHelperStatic.KEY_OPT3_2, opt3);
                contentValues.put(DBHelperStatic.KEY_OPT4_2, opt4);
                contentValues.put(DBHelperStatic.KEY_GENDER_2, gender);

                database.insert(DBHelperStatic.TABLE_TRAINING, null, contentValues);

                break;

            case R.id.clearAllPosts:

                database.delete(DBHelperStatic.TABLE_DIET, null, null);
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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