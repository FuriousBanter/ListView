package com.bookstore.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<ShoppingCartItem> mItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mCartItem;
        public TextView mBookTextView;
        public TextView mAmountTextView;
        public TextView mPriceTextView;

        public ViewHolder(View v) {
            super(v);
            mCartItem = v.findViewById(R.id.cart_list_item);
            mBookTextView = v.findViewById(R.id.book);
            mAmountTextView = v.findViewById(R.id.amount);
            mPriceTextView = v.findViewById(R.id.price);

        }
    }

    private CartActivity mCartActivity;

    public CartAdapter(CartActivity activity, List<ShoppingCartItem> items) {
        mCartActivity = activity;
        mItems = items;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShoppingCartItem c = mItems.get(position);
        holder.mBookTextView.setText((CharSequence) c.getBook().getTitle());
        holder.mAmountTextView.setText("Amount" + c.getQuantity());
        holder.mPriceTextView.setText("Price" + c.getTotalPrice());
    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }
}