package com.bookstore.listview;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<ShoppingCartItem>();
    }

    public List<ShoppingCartItem> getItems() { return this.items; }

    public double getTotalPrice() { //find the total price of your shopping cart and return the value
        double total = 0.0;
        for (int i = 0; i != this.items.size(); i++) {
			ShoppingCartItem item = this.items.get(i);
            total += item.getTotalPrice();
        }
        return total;
    }

    public void add(Book book, int qty) { //add a book to shopping cat, if  book exists, increase the quantity of that book, otherwise store a new book
        ShoppingCartItem item;
		item = this.contains(book);
        if (item != null) {
            int oldQuantity = item.getQuantity();
            item.setQuantity(oldQuantity + qty);
        }
        else {
            item = new ShoppingCartItem(book, qty);
            this.items.add(item);
        }
    }

    public void update(Book book, int qty) {
        ShoppingCartItem item;
        item = this.contains(book);
        if (item != null) {
            if (qty > 0) {
                item.setQuantity(qty);
            }
            else if (qty == 0) {
                this.items.remove(item);
            }
        }
    }

    private ShoppingCartItem contains(Book b) { //gets a particular book id.
        //returns all books then finds one with an id that equals the value passed in (b), then return the book.
        for (int i = 0; i != this.items.size(); i++) {
            ShoppingCartItem item = this.items.get(i);
            if (item.getBook().equals(b)) {
                return item;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this.items.size() == 0;
    }

    public void removeAll() {
        this.items.clear();
    }
}
