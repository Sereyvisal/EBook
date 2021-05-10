package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ebook.Adapter.Interface.TitleClickListener;
import com.example.ebook.Adapter.TitleAdapter;
import com.example.ebook.Model.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private ArrayList<String> titleList;
    private RecyclerView recyclerView;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        DB = new DBHelper(this);
        titleList = new ArrayList<>();

//        mContext.deleteDatabase("EBook");

//        DB.insertBooks("Books", "HTML & CSS");
//        DB.insertBooks("Books", "Angular & React");
//        DB.insertBooks("Books", "C Programming");
//        DB.insertBooks("Books", "C++ Programming");
//        DB.insertBooks("Books", "C# Programming");
//        DB.insertBooks("Books", "ADO.NET Programming");
//        DB.insertBooks("Books", "Python Programming");

//        DB.insertSubBook("HTML_Lesson_1", "file:///android_asset/HTML_Lesson_1.pdf", 1, 0);
//        DB.insertSubBook("HTML_Lesson_2", "file:///android_asset/HTML_Lesson_2.pdf", 1, 0);
//        DB.insertSubBook("HTML_Lesson_3", "file:///android_asset/HTML_Lesson_3.pdf", 1, 0);
//        DB.insertSubBook("HTML_Lesson_4", "file:///android_asset/HTML_Lesson_4.pdf", 1, 0);
//        DB.insertSubBook("HTML_Lesson_5", "file:///android_asset/HTML_Lesson_5.pdf", 1, 0);
//        DB.insertSubBook("HTML_Lesson_6", "file:///android_asset/HTML_Lesson_6.pdf", 1, 0);
//
//        DB.insertSubBook("Angular_&_React_Lesson_1", "file:///android_asset/Angular_&_React_Lesson_1.pdf", 2, 0);
//        DB.insertSubBook("Angular_&_React_Lesson_2", "file:///android_asset/Angular_&_React_Lesson_2.pdf", 2, 0);
//        DB.insertSubBook("Angular_&_React_Lesson_3", "file:///android_asset/Angular_&_React_Lesson_3.pdf", 2, 0);
//        DB.insertSubBook("Angular_&_React_Lesson_4", "file:///android_asset/Angular_&_React_Lesson_4.pdf", 2, 0);
//        DB.insertSubBook("Angular_&_React_Lesson_5", "file:///android_asset/Angular_&_React_Lesson_5.pdf", 2, 0);
//        DB.insertSubBook("Angular_&_React_Lesson_6", "file:///android_asset/Angular_&_React_Lesson_6.pdf", 2, 0);
//
//        DB.insertSubBook("C_Lesson_1", "file:///android_asset/C_Lesson_1.pdf", 3, 0);
//        DB.insertSubBook("C_Lesson_2", "file:///android_asset/C_Lesson_2.pdf", 3, 0);
//        DB.insertSubBook("C_Lesson_3", "file:///android_asset/C_Lesson_3.pdf", 3, 0);
//        DB.insertSubBook("C_Lesson_4", "file:///android_asset/C_Lesson_4.pdf", 3, 0);
//        DB.insertSubBook("C_Lesson_5", "file:///android_asset/C_Lesson_5.pdf", 3, 0);
//        DB.insertSubBook("C_Lesson_6", "file:///android_asset/C_Lesson_6.pdf", 3, 0);
//
//        DB.insertSubBook("C++_Lesson_1", "file:///android_asset/C++_Lesson_1.pdf", 4, 0);
//        DB.insertSubBook("C++_Lesson_2", "file:///android_asset/C++_Lesson_2.pdf", 4, 0);
//        DB.insertSubBook("C++_Lesson_3", "file:///android_asset/C++_Lesson_3.pdf", 4, 0);
//        DB.insertSubBook("C++_Lesson_4", "file:///android_asset/C++_Lesson_4.pdf", 4, 0);
//
//        DB.insertSubBook("C#_Lesson_1", "file:///android_asset/C#_Lesson_1.pdf", 5, 0);
//        DB.insertSubBook("C#_Lesson_2", "file:///android_asset/C#_Lesson_2.pdf", 5, 0);
//        DB.insertSubBook("C#_Lesson_3", "file:///android_asset/C#_Lesson_3.pdf", 5, 0);
//        DB.insertSubBook("C#_Lesson_4", "file:///android_asset/C#_Lesson_4.pdf", 5, 0);
//        DB.insertSubBook("C#_Lesson_5", "file:///android_asset/C#_Lesson_5.pdf", 5, 0);
//        DB.insertSubBook("C#_Lesson_6", "file:///android_asset/C#_Lesson_6.pdf", 5, 0);
//
//        DB.insertSubBook("ADO_Lesson_1", "file:///android_asset/ADO_Lesson_1.pdf", 6, 0);
//        DB.insertSubBook("ADO_Lesson_2", "file:///android_asset/ADO_Lesson_2.pdf", 6, 0);
//        DB.insertSubBook("ADO_Lesson_3", "file:///android_asset/ADO_Lesson_3.pdf", 6, 0);
//        DB.insertSubBook("ADO_Lesson_4", "file:///android_asset/ADO_Lesson_4.pdf", 6, 0);
//        DB.insertSubBook("ADO_Lesson_5", "file:///android_asset/ADO_Lesson_5.pdf", 6, 0);
//        DB.insertSubBook("ADO_Lesson_6", "file:///android_asset/ADO_Lesson_6.pdf", 6, 0);
//
//        DB.insertSubBook("Python_Lesson_1", "file:///android_asset/Python_Lesson_1.pdf", 7, 0);
//        DB.insertSubBook("Python_Lesson_2", "file:///android_asset/Python_Lesson_2.pdf", 7, 0);
//        DB.insertSubBook("Python_Lesson_3", "file:///android_asset/Python_Lesson_3.pdf", 7, 0);
//        DB.insertSubBook("Python_Lesson_4", "file:///android_asset/Python_Lesson_4.pdf", 7, 0);
//        DB.insertSubBook("Python_Lesson_5", "file:///android_asset/Python_Lesson_5.pdf", 7, 0);
//        DB.insertSubBook("Python_Lesson_6", "file:///android_asset/Python_Lesson_6.pdf", 7, 0);

        Cursor res = DB.retriveData("Books", "");
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                titleList.add(res.getString(1));
            }
        }

//        Cursor res2 = DB.retriveData("SubBooks", "isfavorite = 1");
//        if (res2.getCount() > 0) {
//            while (res2.moveToNext()) {
//                titleList.add(res2.getString(1));
//            }
//        }

//        titleList.add(Constants.TITLEA);
//        titleList.add(Constants.TITLEB);
//        titleList.add(Constants.TITLEC);
//        titleList.add(Constants.TITLED);
//        titleList.add(Constants.TITLEE);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        TitleAdapter titleAdapter = new TitleAdapter(mContext, titleList, (itemview, position) -> {
            Intent intent = new Intent(MainActivity.this, ChapterActivity.class);
            intent.putExtra("titles", titleList.get(position));
            startActivity(intent);
        });
        recyclerView.setAdapter(titleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_Tofavorite) {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}