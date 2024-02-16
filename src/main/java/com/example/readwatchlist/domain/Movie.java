package com.example.readwatchlist.domain;

public class Movie extends ConsumableMedia {

    private String location;

    public Movie(String title, String location) {
        super(title);
        this.location = location;
    }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Movie)) {
            return false;
        }

        Movie comparedMovie = (Movie) o;

        String thisTitle = this.getTitle().toLowerCase();
        String thisLocation = this.getLocation().toLowerCase();
        String thatTitle = comparedMovie.getTitle().toLowerCase();
        String thatLocation = comparedMovie.getLocation().toLowerCase();

        return thisTitle.equals(thatTitle) && thisLocation.equals(thatLocation);
    }

}
