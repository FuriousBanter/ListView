package com.bookstore.listview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Model {


    private static Model instance = null;
    public static final String TAG = "MyTag";

    private Context mCtx;
    private RequestQueue mRequestQueue;
    private final List<Book> mBooks;

    public static Model getInstance(Context context) {
        if (instance == null) {
            instance = new Model(context);
        }
        return instance;
    }

    public List<Book> getBooks() {
        return mBooks;
    }

    public Book findBookById(int book_id) {
        Book book = null;
        for (Book b : mBooks) {
            if (b.getId() == book_id) {
                book = b;
                break;
            }
        }
        return book;
    }

    public void addBooks(List<Book> books) {
        for (Book b : books) {
            this.mBooks.add(b);
        }
    }

    public void requestBooks(final RecyclerView.Adapter adapter, String url) {

        JsonArrayRequest stringRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {
                            for (int i = 0; i != jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                String author = jsonObject.getString("author");
                                String isbn = jsonObject.getString("isbn");
                                int year = jsonObject.getInt("year");
                                double price = jsonObject.getDouble("price");

                                Book book = new Book(id, title, author, isbn, year, price);
                                mBooks.add(book);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });

        // Add the request to the RequestQueue.
        this.addRequest(stringRequest);
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    private <T> void addRequest(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelAll() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

    private final ShoppingCart mCart;
    private Model(Context context) {


        mCtx = context;
        mRequestQueue = getRequestQueue();
        mBooks = new ArrayList<Book>();
        mCart = new ShoppingCart();
    }
    public ShoppingCart getCart() {
        return mCart;
    }

}
