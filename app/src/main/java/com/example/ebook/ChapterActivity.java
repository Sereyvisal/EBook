package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ebook.Adapter.TitleAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ChapterActivity extends AppCompatActivity {
    private static final String TAG = "ChapterActivity";
    private Context mContext;
    private ArrayList<String> titleList;
    private RecyclerView recyclerView;
    private DBHelper DB;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        //Back Page Arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = ChapterActivity.this;
        DB = new DBHelper(this);
        extras = getIntent().getExtras();
        titleList = new ArrayList<>();

        if (extras != null) {
            String data = extras.getString("titles");
            Integer bookID = null;

            Objects.requireNonNull(getSupportActionBar()).setTitle(data.replace("_", " "));

            Cursor res1 = DB.retriveData("Books", "title LIKE '%" + data + "%'");
            if (res1.getCount() > 0) {
                while (res1.moveToNext()) {
                    bookID = res1.getInt(0);
                }
            }

            Cursor res2 = DB.retriveData("SubBooks", "parent_book_id = " + bookID);
            if (res2.getCount() > 0) {
                while (res2.moveToNext()) {
                    titleList.add(res2.getString(1));
                }
            }
        }

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        TitleAdapter titleAdapter = new TitleAdapter(mContext, titleList, (itemview, position) -> {
            Intent intent = new Intent(ChapterActivity.this, DetailActivity.class);
            intent.putExtra("Chaptertitles", titleList.get(position));
            startActivity(intent);
        });
        recyclerView.setAdapter(titleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.menu_favorite) {
//            Toast.makeText(mContext, "Added To Favorites", Toast.LENGTH_SHORT).show();
//        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}