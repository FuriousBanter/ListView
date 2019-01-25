package com.bookstore.listview;

//######## What is a fragment? ##########
// a fragment represents the UI of the activity. it can combine multiple fragments in a single activity and can be reused in many activities.
// it is the modular section of the activity. it must always be hosted in an activity and is directly affected by the state of the activity
//e.g., if an activity is frozen, so are the fragments, though when an activity is running, each fragment can be manipulated independently.
//They were designed primarily to support dynamic and flexible interfaces on different systems and devices.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetailFragment extends Fragment  {
    public static final String EXTRA_BOOK_ID = "book_id";

//create a new fragment instance while passing the id of the selected book
    public static BookDetailFragment newInstance(int book_id) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_BOOK_ID, book_id);
        fragment.setArguments(args);
        return fragment;
    }
//initialise the Book class
    private Book mBook;

    public BookDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            //get the book id with a default value, if the book id is not the default value, show the book
            int book_id = args.getInt(EXTRA_BOOK_ID, -1);
            if (book_id != -1) {

                //get instance of the activity while finding the book by id to display in the activity
                mBook = Model.getInstance(this.getActivity()).findBookById(book_id);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //decompresses the fragments layout.
        return inflater.inflate(R.layout.fragment_book_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //find details for book by book id
        TextView mTitleTextView = view.findViewById(R.id.book_title);
        TextView mAuthorTextView = view.findViewById(R.id.book_author);
        TextView mIsbnTextView = view.findViewById(R.id.book_isbn);
        TextView mYearTextView = view.findViewById(R.id.book_year);
        TextView mPriceTextView = view.findViewById(R.id.book_price);
        Button add_to_cart = view.findViewById(R.id.add_to_cart);

        if (mBook != null) {
            //bind values returned from the api and use get methods to return them to the activity.
            mTitleTextView.setText(mBook.getTitle());
            mAuthorTextView.setText(mBook.getAuthor());
            mIsbnTextView.setText(mBook.getIsbn());
            mYearTextView.setText("" + mBook.getYear());
            mPriceTextView.setText("" + mBook.getPrice());
            //when add to cart is clicked, create a success message to user, get the shopping cart and add the book to the cart
            add_to_cart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Added to Your Cart!: " + mBook.getTitle(), Toast.LENGTH_SHORT).show();
                    Model model = Model.getInstance(view.getContext());
                    ShoppingCart cart = model.getCart();

                    cart.add(mBook, 1);
                }
                });
            }

        }

    }


