package com.bookstore.listview;

//######## What is an \adapter? ###########
//an adapter object acts as the bridge between a view and the data for the view. it provides access to the data items.
//the adapter also makes views for each item in the data set


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    //a recycler view is a flexible view for providing a limited window into a large data set.
    //as a user scrolls through the app, off screen data is destroyed as new data is shown on the screen to save
    //phone memory
    private List<Book> mBooks;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //base class for an adapter
        public LinearLayout mBookItem; //a view group that aligns all children in a single direction, vertically or horizontally
        public TextView mTitleTextView;
        public TextView mAuthorTextView;

        public ViewHolder(View v) {
            super(v); //
            mBookItem = v.findViewById(R.id.book_list_item);
            mTitleTextView = v.findViewById(R.id.title);
            mAuthorTextView = v.findViewById(R.id.author);
        }
    }

    private MainActivity mActivity;

    public BookListAdapter(MainActivity activity, List<Book> books) {
        mActivity = activity;
        mBooks = books;
    }

    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //update ViewHolder contents with the item at the given position and sets up private fields for the RecyclerView.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book b = mBooks.get(position);
        holder.mTitleTextView.setText(b.getTitle());
        holder.mAuthorTextView.setText(b.getAuthor());
        holder.mBookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.itemSelected(b);
            }
        });
    }



    @Override
    //return the the amount of books there are in the array
    public int getItemCount() {
        return mBooks.size();
    }
}