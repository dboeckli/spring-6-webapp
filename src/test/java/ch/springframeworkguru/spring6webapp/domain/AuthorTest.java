package ch.springframeworkguru.spring6webapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author1;
    private Author author2;

    @BeforeEach
    void setUp() {
        author1 = new Author();
        author2 = new Author();
    }


    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, author1);
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals(new Object(), author1);
    }

    @Test
    void testEqualsWithDifferentIds() {
        author1.setId(1L);
        author2.setId(2L);
        assertNotEquals(author1, author2);
    }

    @Test
    void testEqualsWithSameIds() {
        author1.setId(1L);
        author2.setId(1L);
        assertEquals(author1, author2);
    }

    @Test
    void testEqualsWithNullIds() {
        assertEquals(author1, author2);
    }

    @Test
    void testEqualsWithOneNullId() {
        author1.setId(1L);
        assertNotEquals(author1, author2);
    }
}
