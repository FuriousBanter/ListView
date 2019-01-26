package com.bookstore.listview; //name of the app package

class Book { //this is a class that creates book objects
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int year;
    private double price;
   // initialise all the variables for the object

    public Book(int id, String title, String author, String isbn, int year, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
    }
    //creates an array using data passed in from the model constructor

    public int getId() {
        return id;
    }
//function that gets the id of the book
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    //function that gets the author of the book from the database

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //function that sets the isbn of the book

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
