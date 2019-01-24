package com.bookstore.listview;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BookDetailActivity extends AppCompatActivity {

    private int book_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bookstore Details");


        Intent intent = getIntent();
        book_id = intent.getIntExtra(BookDetailFragment.EXTRA_BOOK_ID, -1);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.book_details_one_pane, BookDetailFragment.newInstance(book_id));
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_favorite:
                Toast.makeText(getApplicationContext(),"View cart selected",Toast.LENGTH_LONG).show();
                Intent intent = new Intent (this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_account:
                Toast.makeText(getApplicationContext(),"Account selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"Settings Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}