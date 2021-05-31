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

public class Posts3 extends AppCompatActivity implements View.OnClickListener {


    DBHelperStatic dbHelper;

    private Toolbar mToolbar;
    private Button changePostBtn;
    private TextView currDate;

    private String[][] dbArr;

    int idxPost1 = 1;



    int countRows = 0;

    private TextView opt1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts3);


        dbHelper = new DBHelperStatic(this);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        currDate = findViewById(R.id.currDate);
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        currDate.setText("Сегодня\n" + timeStamp);



        changePostBtn = findViewById(R.id.changePostBtn);
        changePostBtn.setOnClickListener(this);

        opt1 = findViewById(R.id.textOpt1);



        init();


        opt1.setText(dbArr[idxPost1][2]);

    }

    public void init() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        Cursor cursor = database.rawQuery("select * from facts", null);
        Cursor st = database.rawQuery("select COUNT(*) from facts", null);


        if (st.moveToFirst()) {
            countRows = st.getInt(0);
        }

        dbArr = new String[countRows][9];


        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelperStatic.KEY_ID);
            int opt1Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT1);



            int i = 0;

            do {

                if (i < countRows) {
                    dbArr[i][0] = cursor.getInt(idIndex) + "";
                    dbArr[i][2] = cursor.getString(opt1Index);


                }

                i++;

                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", opt1 = " + cursor.getString(opt1Index)
                       );

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
            case R.id.changePostBtn:

                int fromNum = 0;
                int toNum = countRows;

                int random_number = fromNum + (int) (Math.random() * toNum);


                int randRes = showPosts(countRows, random_number + "");


                int randIdxPost1 = randRes;


                opt1.setText(dbArr[randIdxPost1][2]);




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