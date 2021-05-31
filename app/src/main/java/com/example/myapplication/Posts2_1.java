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

public class Posts2_1 extends AppCompatActivity implements View.OnClickListener {


    DBHelperStatic dbHelper;

    private Toolbar mToolbar;
    private Button backBtn;
    private Button changePostBtn;
    private TextView currDate;

    private String[][] dbArr;

    int idxPost1 = 1;



    int countRows = 0;

    private TextView opt1;
    private TextView opt2;
    private TextView opt3;
    private TextView opt4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts2_1);


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



        init();


        opt1.setText("1. " + dbArr[idxPost1][2]);
        opt2.setText("2. " + dbArr[idxPost1][3]);
        opt3.setText("3. " + dbArr[idxPost1][4]);
        opt4.setText("Среднее время тренировки: " + dbArr[idxPost1][5]);

    }

    public void init() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        Cursor cursor = database.rawQuery("select * from training where type = ? and gender = ?", new String[]{"muscle", "man"});
        Cursor st = database.rawQuery("select COUNT(*) from training where type = ? and gender = ?", new String[]{"muscle", "man"});


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
                    dbArr[i][8] = cursor.getString(genderIndex);

                }

                i++;

                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", type = " + cursor.getString(typeIndex) +
                        ", opt1 = " + cursor.getString(opt1Index) +
                        ", opt2 = " + cursor.getString(opt2Index) +
                        ", opt3 = " + cursor.getString(opt3Index) +
                        ", opt4 = " + cursor.getString(opt4Index) +
                        ", gender = " + cursor.getString(genderIndex));

            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }

        cursor.close();


        String timeStamp2 = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());


        int res = showPosts(countRows, timeStamp2);

        idxPost1 = res;



    }


    public int showPosts(int countRows_s, String timeStamp2) {

        int idxPost1 = 1;


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



        return idxPost1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:

                Intent intent = new Intent(this, WithCases2_1.class);
                startActivity(intent);
                break;
            case R.id.changePostBtn:

                int fromNum = 0;
                int toNum = countRows;

                int random_number = fromNum + (int) (Math.random() * toNum);


                int randRes = showPosts(countRows, random_number + "");


                int randIdxPost1 = randRes;


                opt1.setText("1. " + dbArr[randIdxPost1][2]);
                opt2.setText("2. " + dbArr[randIdxPost1][3]);
                opt3.setText("3. " + dbArr[randIdxPost1][4]);
                opt4.setText("Среднее время тренировки: " + dbArr[randIdxPost1][5]);




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