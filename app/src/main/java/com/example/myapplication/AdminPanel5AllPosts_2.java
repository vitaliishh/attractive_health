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

public class AdminPanel5AllPosts_2 extends AppCompatActivity {

    DBHelperStatic dbHelper;

    private Toolbar mToolbar;


    private final List<AdminPanel5AllPosts_2.ItemAllPosts> items = new ArrayList<>();
    private final List<AdminPanel5AllPosts_2.ItemAllPosts2> items2 = new ArrayList<>();
    private final List<AdminPanel5AllPosts_2.ItemAllPosts3> items3 = new ArrayList<>();
    private final List<AdminPanel5AllPosts_2.ItemAllPosts4> items4 = new ArrayList<>();
    private final List<AdminPanel5AllPosts_2.ItemAllPosts5> items5 = new ArrayList<>();
    private final List<AdminPanel5AllPosts_2.ItemAllPosts6> items6 = new ArrayList<>();
    private final List<AdminPanel5AllPosts_2.ItemAllPosts9> items9 = new ArrayList<>();

    private final RecyclerView.Adapter adapter = new AdminPanel5AllPosts_2.ItemAdapter(this.items, this.items2, this.items3, this.items4, this.items5, this.items6, this.items9);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel5_all_posts_2);


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

        Cursor cursor = database.query(DBHelperStatic.TABLE_TRAINING, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelperStatic.KEY_ID_2);
            int typeIndex = cursor.getColumnIndex(DBHelperStatic.KEY_TYPE_2);
            int opt1Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT1_2);
            int opt2Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT2_2);
            int opt3Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT3_2);
            int opt4Index = cursor.getColumnIndex(DBHelperStatic.KEY_OPT4_2);
            int genderIndex = cursor.getColumnIndex(DBHelperStatic.KEY_GENDER_2);


            do {

                this.items.add(new AdminPanel5AllPosts_2.ItemAllPosts("id " + (cursor.getInt(idIndex)) + ""));
                this.items2.add(new AdminPanel5AllPosts_2.ItemAllPosts2(cursor.getString(typeIndex)));
                this.items3.add(new AdminPanel5AllPosts_2.ItemAllPosts3(cursor.getString(opt1Index)));
                this.items4.add(new AdminPanel5AllPosts_2.ItemAllPosts4(cursor.getString(opt2Index)));
                this.items5.add(new AdminPanel5AllPosts_2.ItemAllPosts5(cursor.getString(opt3Index)));
                this.items6.add(new AdminPanel5AllPosts_2.ItemAllPosts6(cursor.getString(opt4Index)));
                this.items9.add(new AdminPanel5AllPosts_2.ItemAllPosts9(cursor.getString(genderIndex)));


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
        private final List<AdminPanel5AllPosts_2.ItemAllPosts> items;
        private final List<AdminPanel5AllPosts_2.ItemAllPosts2> items2;
        private final List<AdminPanel5AllPosts_2.ItemAllPosts3> items3;
        private final List<AdminPanel5AllPosts_2.ItemAllPosts4> items4;
        private final List<AdminPanel5AllPosts_2.ItemAllPosts5> items5;
        private final List<AdminPanel5AllPosts_2.ItemAllPosts6> items6;
        private final List<AdminPanel5AllPosts_2.ItemAllPosts9> items9;


        public ItemAdapter(List<AdminPanel5AllPosts_2.ItemAllPosts> items, List<AdminPanel5AllPosts_2.ItemAllPosts2> items2, List<AdminPanel5AllPosts_2.ItemAllPosts3> items3, List<AdminPanel5AllPosts_2.ItemAllPosts4> items4, List<AdminPanel5AllPosts_2.ItemAllPosts5> items5, List<AdminPanel5AllPosts_2.ItemAllPosts6> items6, List<AdminPanel5AllPosts_2.ItemAllPosts9> items9) {
            this.items = items;
            this.items2 = items2;
            this.items3 = items3;
            this.items4 = items4;
            this.items5 = items5;
            this.items6 = items6;
            this.items9 = items9;

        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.recycler_item_admin5_2, parent, false)
            ) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView name = holder.itemView.findViewById(R.id.id1);
            name.setText(this.items.get(position).getName());

            TextView name2 = holder.itemView.findViewById(R.id.id2);
            name2.setText(this.items2.get(position).getName());

            TextView name3 = holder.itemView.findViewById(R.id.id3);
            name3.setText(this.items3.get(position).getName());

            TextView name4 = holder.itemView.findViewById(R.id.id4);
            name4.setText(this.items4.get(position).getName());

            TextView name5 = holder.itemView.findViewById(R.id.id5);
            name5.setText(this.items5.get(position).getName());


            TextView name6 = holder.itemView.findViewById(R.id.id6);
            name6.setText(this.items6.get(position).getName());


            TextView name9 = holder.itemView.findViewById(R.id.id9);
            name9.setText(this.items9.get(position).getName());
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


    public static class ItemAllPosts2 {
        private String name;

        public ItemAllPosts2(String name) {
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

    public static class ItemAllPosts4 {
        private String name;

        public ItemAllPosts4(String name) {
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

    public static class ItemAllPosts5 {
        private String name;

        public ItemAllPosts5(String name) {
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

    public static class ItemAllPosts6 {
        private String name;

        public ItemAllPosts6(String name) {
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


    public static class ItemAllPosts9 {
        private String name;

        public ItemAllPosts9(String name) {
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