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
import android.widget.Toast;

import com.example.ebook.Adapter.TitleAdapter;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private static final String TAG = "FavoriteActivity";
    private Context mContext;
    private ArrayList<String> titleList;
    private RecyclerView recyclerView;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        //Back Page Arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Change ActionBar Title
        getSupportActionBar().setTitle("Favorites");

        mContext = FavoriteActivity.this;
        DB = new DBHelper(this);
        titleList = new ArrayList<>();

        Cursor res = DB.retriveData("SubBooks", "isfavorite = 1");
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                titleList.add(res.getString(1));
            }
        }

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        TitleAdapter titleAdapter = new TitleAdapter(mContext, titleList, (itemview, position) -> {
            Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
            intent.putExtra("Chaptertitles", titleList.get(position));
            startActivity(intent);
        });
        recyclerView.setAdapter(titleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}