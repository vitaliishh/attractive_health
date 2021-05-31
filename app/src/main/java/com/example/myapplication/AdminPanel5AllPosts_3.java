package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminPanel5AllPosts_3 extends AppCompatActivity {

    DBHelperStatic dbHelper;

    private Toolbar mToolbar;

    private final List<AdminPanel5AllPosts_3.ItemAllPosts> items = new ArrayList<>();
    private final List<AdminPanel5AllPosts_3.ItemAllPosts3> items3 = new ArrayList<>();

    private final RecyclerView.Adapter adapter = new AdminPanel5AllPosts_3.ItemAdapter(this.items, this.items3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel5_all_posts_3);


        dbHelper = new DBHelperStatic(this);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        fillArrFromDB();


        RecyclerView elemList;
        elemList = findViewById(R.id.recycle_elem);
        elemList.setHasFixedSize(true);
        elemList.setLayoutManager(new LinearLayoutManager(this));
        elemList.setAdapter(adapter);

//
//        this.items.add(new ItemAllPosts("dedd"));
//        this.items2.add(new ItemAllPosts2("dedd2222222222"));
//        this.items3.add(new ItemAllPosts3("3333dedd2222222222"));
//        this.items4.add(new ItemAllPosts4("4dedd2222222222"));
//        this.items5.add(new ItemAllPosts5("5555dedd2222222222"));
//        this.items6.add(new ItemAllPosts6("6665555dedd2222222222"));
//        this.items9.add(new ItemAllPosts9("man"));


        adapter.notifyItemInserted(this.items.size() - 1);
    }


    public void fillArrFromDB() {


        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelperStatic.TABLE_FACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelperStatic.KEY_ID_3);
            int opt1Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT1_3);


            do {

                this.items.add(new AdminPanel5AllPosts_3.ItemAllPosts("id " + (cursor.getInt(idIndex)) + ""));
                this.items3.add(new AdminPanel5AllPosts_3.ItemAllPosts3(cursor.getString(opt1Index)));


//                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
//                        ", type = " + cursor.getString(typeIndex) +
//                        ", opt1 = " + cursor.getString(opt1Index) +
//                        ", opt2 = " + cursor.getString(opt2Index) +
//                        ", opt3 = " + cursor.getString(opt3Index) +
//                        ", opt4 = " + cursor.getString(opt4Index) +
//                        ", gender = " + cursor.getString(genderIndex));

            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }

        cursor.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<AdminPanel5AllPosts_3.ItemAllPosts> items;
        private final List<AdminPanel5AllPosts_3.ItemAllPosts3> items3;



        public ItemAdapter(List<AdminPanel5AllPosts_3.ItemAllPosts> items, List<AdminPanel5AllPosts_3.ItemAllPosts3> items3) {
            this.items = items;
            this.items3 = items3;


        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.recycler_item_admin5_3, parent, false)
            ) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView name = holder.itemView.findViewById(R.id.id1);
            name.setText(this.items.get(position).getName());


            TextView name3 = holder.itemView.findViewById(R.id.id3);
            name3.setText(this.items3.get(position).getName());

        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }


    public static class ItemAllPosts {
        private String name;

        public ItemAllPosts(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }



    public static class ItemAllPosts3 {
        private String name;

        public ItemAllPosts3(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int hashCode() {
            return Objects.hash(name);
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