package com.example.readwatchlist.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie americanFiction;
    private Movie callMe;

    @BeforeEach
    void setUp() {
        americanFiction = new Movie("American Fiction", "Netflix");
        callMe = new Movie("Call Me By Your Name", "Hulu");
    }

    @Test
    void moviesAreEqualIfTheyAreSameObject() {
        Object o = americanFiction;
        assertEquals(o, americanFiction);
    }

    @Test
    void moviesAreNotEqualIfTheyHaveDifferentTitles() {
        assertNotEquals(americanFiction, callMe);
    }

    @Test
    void moviesAreEqualIfTitleAndLocationMatch() {
        Movie americanFiction2 = new Movie("american fiction", "netflix");
        assertEquals(americanFiction, americanFiction2);
    }



}