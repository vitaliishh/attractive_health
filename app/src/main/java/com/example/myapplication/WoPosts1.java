package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WoPosts1 extends AppCompatActivity implements View.OnClickListener {


    DBHelperStatic dbHelper;

    private Toolbar mToolbar;
    private Button backBtn;
    private Button changePostBtn;
    private TextView currDate;

    private String[][] dbArr;

    int idxPost1 = 1;
    int idxPost2 = 1;
    int idxPost3 = 1;


    int countRows = 0;

    private TextView opt1;
    private TextView opt2;
    private TextView opt3;
    private TextView opt4;
    private TextView opt5;
    private TextView opt6;
    private TextView opt7;
    private TextView opt8;
    private TextView opt9;
    private TextView opt10;
    private TextView opt11;
    private TextView opt12;
    private TextView opt13;
    private TextView opt14;
    private TextView opt15;
    private TextView opt16;
    private TextView opt17;
    private TextView opt18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_posts1);


        dbHelper = new DBHelperStatic(this);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        currDate = findViewById(R.id.currDate);
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        currDate.setText("Сегодня\n" + timeStamp);


        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        changePostBtn = findViewById(R.id.changePostBtn);
        changePostBtn.setOnClickListener(this);

        opt1 = findViewById(R.id.textOpt1);
        opt2 = findViewById(R.id.textOpt2);
        opt3 = findViewById(R.id.textOpt3);
        opt4 = findViewById(R.id.textOpt4);
        opt5 = findViewById(R.id.textOpt5);
        opt6 = findViewById(R.id.textOpt6);
        opt7 = findViewById(R.id.textOpt7);
        opt8 = findViewById(R.id.textOpt8);
        opt9 = findViewById(R.id.textOpt9);
        opt10 = findViewById(R.id.textOpt10);
        opt11 = findViewById(R.id.textOpt11);
        opt12 = findViewById(R.id.textOpt12);
        opt13 = findViewById(R.id.textOpt13);
        opt14 = findViewById(R.id.textOpt14);
        opt15 = findViewById(R.id.textOpt15);
        opt16 = findViewById(R.id.textOpt16);
        opt17 = findViewById(R.id.textOpt17);
        opt18 = findViewById(R.id.textOpt18);


        init();


        opt1.setText("1. " + dbArr[idxPost1][2]);
        opt2.setText("2. " + dbArr[idxPost1][3]);
        opt3.setText("3. " + dbArr[idxPost1][4]);
        opt4.setText("Калорийность: " + dbArr[idxPost1][5]);
        opt5.setText("Цена: " + dbArr[idxPost1][6]);
        opt6.setText("Время приготовления: " + dbArr[idxPost1][7]);


        opt7.setText("1. " + dbArr[idxPost2][2]);
        opt8.setText("2. " + dbArr[idxPost2][3]);
        opt9.setText("3. " + dbArr[idxPost2][4]);
        opt10.setText("Калорийность: " + dbArr[idxPost2][5]);
        opt11.setText("Цена: " + dbArr[idxPost2][6]);
        opt12.setText("Время приготовления: " + dbArr[idxPost2][7]);


        opt13.setText("1. " + dbArr[idxPost3][2]);
        opt14.setText("2. " + dbArr[idxPost3][3]);
        opt15.setText("3. " + dbArr[idxPost3][4]);
        opt16.setText("Калорийность: " + dbArr[idxPost3][5]);
        opt17.setText("Цена: " + dbArr[idxPost3][6]);
        opt18.setText("Время приготовления: " + dbArr[idxPost3][7]);

    }

    public void init() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        Cursor cursor = database.rawQuery("select * from diet where type = ? and gender = ?", new String[]{"standart", "woman"});
        Cursor st = database.rawQuery("select COUNT(*) from diet where type = ? and gender = ?", new String[]{"standart", "woman"});


        if (st.moveToFirst()) {
            countRows = st.getInt(0);
        }

        dbArr = new String[countRows][9];


        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelperStatic.KEY_ID);
            int typeIndex = cursor.getColumnIndex(DBHelperStatic.KEY_TYPE);
            int opt1Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT1);
            int opt2Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT2);
            int opt3Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT3);
            int opt4Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT4);
            int opt5Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT5);
            int opt6Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT6);
            int genderIndex = cursor.getColumnIndex(DBHelperStatic.KEY_GENDER);


            int i = 0;

            do {

                if (i < countRows) {
                    dbArr[i][0] = cursor.getInt(idIndex) + "";
                    dbArr[i][1] = cursor.getString(typeIndex);
                    dbArr[i][2] = cursor.getString(opt1Index);
                    dbArr[i][3] = cursor.getString(opt2Index);
                    dbArr[i][4] = cursor.getString(opt3Index);
                    dbArr[i][5] = cursor.getString(opt4Index);
                    dbArr[i][6] = cursor.getString(opt5Index);
                    dbArr[i][7] = cursor.getString(opt6Index);
                    dbArr[i][8] = cursor.getString(genderIndex);

                }

                i++;

                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", type = " + cursor.getString(typeIndex) +
                        ", opt1 = " + cursor.getString(opt1Index) +
                        ", opt2 = " + cursor.getString(opt2Index) +
                        ", opt3 = " + cursor.getString(opt3Index) +
                        ", opt4 = " + cursor.getString(opt4Index) +
                        ", opt5 = " + cursor.getString(opt5Index) +
                        ", opt6 = " + cursor.getString(opt6Index) +
                        ", gender = " + cursor.getString(genderIndex));

            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }

        cursor.close();


        String timeStamp2 = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());


        int[] res = showPosts(countRows, timeStamp2);

        idxPost1 = res[0];
        idxPost2 = res[1];
        idxPost3 = res[2];


    }


    public int[] showPosts(int countRows_s, String timeStamp2) {

        int idxPost1 = 1;
        int idxPost2 = 1;
        int idxPost3 = 1;


        int currDay = Integer.parseInt(timeStamp2);


        int k = currDay;

        if (currDay >= countRows_s) {
            if (currDay != 0) {

                while (k >= countRows_s) {
                    k = k / 2;
                }

                idxPost1 = k;
            }
        } else {
            idxPost1 = currDay;
        }


        if ((idxPost1 - 1) >= 0 && (idxPost1 - 1) < countRows_s) {
            idxPost2 = (idxPost1 - 1);
        } else if ((idxPost1 + 1) >= 0 && (idxPost1 + 1) < countRows_s) {
            idxPost2 = (idxPost1 + 1);
        } else {
            idxPost2 = idxPost1;
        }

        if ((idxPost1 - 2) >= 0 && (idxPost1 - 2) < countRows_s) {
            idxPost3 = (idxPost1 - 2);
        } else if ((idxPost1 + 2) >= 0 && (idxPost1 + 2) < countRows_s) {
            idxPost3 = (idxPost1 + 2);
        } else {
            idxPost3 = idxPost1;
        }


        return new int[]{idxPost1, idxPost2, idxPost3};
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:

                Intent intent = new Intent(this, WoWithCases2.class);
                startActivity(intent);
                break;
            case R.id.changePostBtn:

                int fromNum = 0;
                int toNum = countRows;

                int random_number = fromNum + (int) (Math.random() * toNum);


                int[] randRes = showPosts(countRows, random_number + "");


                int randIdxPost1 = randRes[0];
                int randIdxPost2 = randRes[1];
                int randIdxPost3 = randRes[2];

                opt1.setText("1. " + dbArr[randIdxPost1][2]);
                opt2.setText("2. " + dbArr[randIdxPost1][3]);
                opt3.setText("3. " + dbArr[randIdxPost1][4]);
                opt4.setText("Калорийность: " + dbArr[randIdxPost1][5]);
                opt5.setText("Цена: " + dbArr[randIdxPost1][6]);
                opt6.setText("Время приготовления: " + dbArr[randIdxPost1][7]);


                opt7.setText("1. " + dbArr[randIdxPost2][2]);
                opt8.setText("2. " + dbArr[randIdxPost2][3]);
                opt9.setText("3. " + dbArr[randIdxPost2][4]);
                opt10.setText("Калорийность: " + dbArr[randIdxPost2][5]);
                opt11.setText("Цена: " + dbArr[randIdxPost2][6]);
                opt12.setText("Время приготовления: " + dbArr[randIdxPost2][7]);


                opt13.setText("1. " + dbArr[randIdxPost3][2]);
                opt14.setText("2. " + dbArr[randIdxPost3][3]);
                opt15.setText("3. " + dbArr[randIdxPost3][4]);
                opt16.setText("Калорийность: " + dbArr[randIdxPost3][5]);
                opt17.setText("Цена: " + dbArr[randIdxPost3][6]);
                opt18.setText("Время приготовления: " + dbArr[randIdxPost3][7]);

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