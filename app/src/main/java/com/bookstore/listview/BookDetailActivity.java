package com.bookstore.listview;
//####### What is an activity? ########
//activities serve as the entry point for user interface within the app. they are central to how a user navigates an app.
//they handle smooth orientation changes, retaining data during transactions and kills unneeded processes.
//Unlike most desktop apps, mobile apps can be launched at different points in the app’s lifecycle, e.g., an app that opens into a video streaming app that lists recommended videos,
// which could be alternatively opened by, for example, following a link which opens the app directly into a video, bypassing the list of recommended videos in the main activity.
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//import external widgets and support libraries

public class BookDetailActivity extends AppCompatActivity {
//uses the appCompatActivity super class which has support for the action bar.
    private int book_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //called when the activity is first created. provides a bundle containing the
        // activities previous frozen state if one exists.
        //calls the onCreate in the super while passing in the savedInstanceState
        super.onCreate(savedInstanceState);
        //load saved state of this activity
        setContentView(R.layout.activity_book_detail);
//make the view activity_book_detail
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bookstore Details");
        //initialise the action bar and name it bookstore details

//intent launches activities from another activity., it is a bridge between different activities. holds an
        //abstract description of action to be performed
        Intent intent = getIntent();
        book_id = intent.getIntExtra(BookDetailFragment.EXTRA_BOOK_ID, -1);
//starts a transaction to launch an activity
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
        //when an item in the action bar is selected: do one of these
        int id = item.getItemId();
        switch (id){
            case R.id.action_favorite: //*action_Cart
                //show a message that tells a user that the button they pressed in the action bar is selected
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