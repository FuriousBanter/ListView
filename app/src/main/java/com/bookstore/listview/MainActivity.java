package com.bookstore.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.MenuInflater;

import java.util.List;
//this is the activity that an app launches into by default. it is the first thing a user will typically sees after launching the app.
public class MainActivity extends AppCompatActivity implements BookListFragment.ItemListener {
//two pane sets up the different arrangement of the app fragments if the display is landscape instead of portrait(onepane)
    private boolean mTwoPane = false;
    private Book mSelectedBook = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Bookstore");

        int savedBookId = -1;
        if (savedInstanceState != null){
            savedBookId = savedInstanceState.getInt(BookDetailFragment.EXTRA_BOOK_ID, -1);
        }

        List<Book> books = Model.getInstance(this).getBooks();
        if (!books.isEmpty()){
            if (savedBookId == -1) {
                mSelectedBook = Model.getInstance(this).getBooks().get(0);
            } else {
                mSelectedBook = Model.getInstance(this).findBookById(savedBookId);
            }
        }


        mTwoPane = (findViewById(R.id.book_details_two_pane) != null);
        if (mTwoPane && mSelectedBook != null) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.book_details_two_pane, BookDetailFragment.newInstance(mSelectedBook.getId()));
                ft.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_toolbar, menu); //calls the action bar in the resources folder. R means resource.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_favorite:
                Toast.makeText(this,"View cart selected",Toast.LENGTH_LONG).show();
                Intent intent = new Intent (this, CartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_account:
                Toast.makeText(this,"Account selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this,"Settings Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void itemSelected(Book b) { //if an item is selected, create an intent to start the book details activity
        mSelectedBook = b;
        if (mTwoPane) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.book_details_two_pane, BookDetailFragment.newInstance(b.getId()));
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra(BookDetailFragment.EXTRA_BOOK_ID, b.getId());

            startActivity(intent); //starts book detail activity with the id of the selected book which is used to show the particular book in the new activity.
        }
    }


    public void onSaveInstanceState(Bundle savedInstanceState) { //preserves the state of the activity

        if (mSelectedBook != null) {
            savedInstanceState.putInt(BookDetailFragment.EXTRA_BOOK_ID, mSelectedBook.getId());
        }
        super.onSaveInstanceState(savedInstanceState);
    }
}
