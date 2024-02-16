package com.example.readwatchlist.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Book platosRepublic;
    Book zenAndTheArt;

    @BeforeEach
    void setUp() {
        platosRepublic = new Book("Republic", "Plato");
        zenAndTheArt = new Book("Zen and the Art of Motorcycle Maintenance", "Robert Pirsig");
    }

    @Test
    void booksAreEqualIfTheyAreSameObject() {
        Object o = platosRepublic;
        assertEquals(o, platosRepublic);
    }

    @Test
    void booksAreNotEqualIfTheyHaveDifferentTitles() {
        assertNotEquals(platosRepublic, zenAndTheArt);
    }

    @Test
    void booksAreEqualIfTitleAndAuthorMatch() {
        Book platosRepublic2 = new Book("republic", "plato");
        assertEquals(platosRepublic, platosRepublic2);
    }

}