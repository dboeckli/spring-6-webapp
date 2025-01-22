package ch.springframeworkguru.spring6webapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BookTest {

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        book1 = new Book();
        book2 = new Book();
    }


    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, book1);
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals(new Object(), book1);
    }

    @Test
    void testEqualsWithDifferentIds() {
        book1.setId(1L);
        book2.setId(2L);
        assertNotEquals(book1, book2);
    }

    @Test
    void testEqualsWithSameIds() {
        book1.setId(1L);
        book2.setId(1L);
        assertEquals(book1, book2);
    }

    @Test
    void testEqualsWithNullIds() {
        assertEquals(book1, book2);
    }

    @Test
    void testEqualsWithOneNullId() {
        book1.setId(1L);
        assertNotEquals(book1, book2);
    }
}
