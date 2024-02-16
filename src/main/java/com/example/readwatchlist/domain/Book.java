package com.example.readwatchlist.domain;

public class Book extends ConsumableMedia {

    private String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Book)) {
            return false;
        }

        Book comparedBook = (Book) o;

        String thisTitle = this.getTitle().toLowerCase();
        String thatTitle = comparedBook.getTitle().toLowerCase();
        String thisAuthor = this.author.toLowerCase();
        String thatAuthor = comparedBook.author.toLowerCase();

        return thisTitle.equals(thatTitle) && thisAuthor.equals(thatAuthor);
    }

}
