package com.bookstore.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<ShoppingCartItem> mItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mCartItem;
        public TextView mBookTextView;
        public TextView mQuantityTextView;
        public TextView mTotalPriceTextView;
        public Button mAddBtn;
        public Button mRemoveBtn;

        public ViewHolder(View v) {
            super(v);
            mCartItem = v.findViewById(R.id.cart_list_item);
            mBookTextView = v.findViewById(R.id.book);
            mQuantityTextView = v.findViewById(R.id.quantity);
            mTotalPriceTextView = v.findViewById(R.id.totalPrice);
            mAddBtn = v.findViewById(R.id.btnAdd);
            mRemoveBtn = v.findViewById(R.id.btnRemove);

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
        holder.mQuantityTextView.setText("Quantity" + c.getQuantity());
        holder.mTotalPriceTextView.setText("TotalPrice" + c.getTotalPrice());

        holder.mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = c.getQuantity();
                c.setQuantity(quantity + 1);

                CartAdapter.this.notifyDataSetChanged();
            }
        });

        holder.mRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = c.getQuantity();
                if (quantity > 1) {
                    c.setQuantity(quantity - 1);
                }
                else if (quantity == 1){
                    mItems.remove(c);
                }
                CartAdapter.this.notifyDataSetChanged();
            }
        });
    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }
}