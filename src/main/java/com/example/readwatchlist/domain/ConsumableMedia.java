package com.example.readwatchlist.domain;

public abstract class ConsumableMedia {

    private String title;

    public ConsumableMedia(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public abstract boolean equals(Object o);

}
