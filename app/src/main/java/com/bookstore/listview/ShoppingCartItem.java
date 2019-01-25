package com.bookstore.listview;

public class ShoppingCartItem {
    private Book book;
    private int quantity;
//create a new item in the shopping cart and store the quantity of said book.
    public ShoppingCartItem(Book book, int qty) {
        this.book = book;
        this.quantity = qty;
    }
//gets and sets for book objects
    public Book getBook() { return this.book; }
    public int getQuantity() { return this.quantity; }
    public double getTotalPrice() { return this.book.getPrice() * this.quantity; }
    public void setQuantity(int qty) { this.quantity = qty; }
}
