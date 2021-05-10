package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "Description";
    private Context context;
    private WebView webView;
    private Bundle extras;
    private DBHelper DB;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Back Page Arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = DetailActivity.this;
        extras = getIntent().getExtras();
        DB = new DBHelper(this);

        //////  Web Viewer  //////
//        webView = findViewById(R.id.webview);
//        if (extras != null) {
//            String data = extras.getString("Chaptertitles");
//            Log.d(TAG, "onCreate: the coming data is " + data);
//            String url = "file:///android_asset/" + data + ".html";
//            webView.loadUrl(url);
//
//            WebSettings webSettings = webView.getSettings();
//            webSettings.setBuiltInZoomControls(true);
//            webSettings.setDisplayZoomControls(false);
//
//            Objects.requireNonNull(getSupportActionBar()).setTitle(data.replace("_", " "));
//        }


        //////  PDF Viewer  //////

        PDFView pdfView = findViewById(R.id.pdfview);
        String path = "";

        if (extras != null) {
            String data = extras.getString("Chaptertitles");
            Log.d(TAG, "onCreate: the coming data is " + data);
            path = "file:///android_asset/" + data + ".pdf";

            Objects.requireNonNull(getSupportActionBar()).setTitle(data.replace("_", " "));


            pdfView.fromAsset(data + ".pdf")
                 // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
//                // allows to draw something on the current page, usually visible in the middle of the screen
//                .onDraw(onDrawListener)
//                // allows to draw something on all pages, separately for every page. Called only for visible pages
//                .onDrawAll(onDrawListener)
//                .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                .onPageChange(onPageChangeListener)
//                .onPageScroll(onPageScrollListener)
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(onRenderListener) // called after document is rendered for the first time
//                // called on single tap, return true if handled, false to toggle scroll handle visibility
//                .onTap(onTapListener)
//                .onLongPress(onLongPressListener)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
//                .linkHandler(DefaultLinkHandler)
//                .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                .pageSnap(false) // snap pages to screen boundaries
                .pageFling(false) // make a fling change only a single page like ViewPager
                .nightMode(false) // toggle night mode
                .load();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int isfavorite = 0;
        String chapterTitle = getSupportActionBar().getTitle().toString().replace(" ", "_");
        Cursor res = DB.retriveData("SubBooks", "title LIKE '%" + chapterTitle + "%'");
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                isfavorite = res.getInt(4);
            }
        }
        if (isfavorite == 0) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        else if (isfavorite == 1) {
            getMenuInflater().inflate(R.menu.remove_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_favorite) {
            String chapterTitle = getSupportActionBar().getTitle().toString().replace(" ", "_");
            DB.updateSubBook(chapterTitle, 1);
            Toast.makeText(context, "Added To Favorites", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_removefavorite) {
            String chapterTitle = getSupportActionBar().getTitle().toString().replace(" ", "_");
            DB.updateSubBook(chapterTitle, 0);
            Toast.makeText(context, "Removed From Favorites", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}