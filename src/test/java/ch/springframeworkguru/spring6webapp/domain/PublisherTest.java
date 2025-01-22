package ch.springframeworkguru.spring6webapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    private Publisher publisher1;
    private Publisher publisher2;

    @BeforeEach
    void setUp() {
        publisher1 = new Publisher();
        publisher2 = new Publisher();
    }


    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, publisher1);
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals(new Object(), publisher1);
    }

    @Test
    void testEqualsWithDifferentIds() {
        publisher1.setId(1L);
        publisher2.setId(2L);
        assertNotEquals(publisher1, publisher2);
    }

    @Test
    void testEqualsWithSameIds() {
        publisher1.setId(1L);
        publisher2.setId(1L);
        assertEquals(publisher1, publisher2);
    }

    @Test
    void testEqualsWithNullIds() {
        assertEquals(publisher1, publisher2);
    }

    @Test
    void testEqualsWithOneNullId() {
        publisher1.setId(1L);
        assertNotEquals(publisher1, publisher2);
    }
}
