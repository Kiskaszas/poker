package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class ErtekelesTest {

    private Ertekeles ertekeles;

    @BeforeEach
    void setUp() {
        ertekeles = new Ertekeles();
    }

    @Test
    void testIsRoyalFlush() {
        List<Lap> lapok = Arrays.asList(
                new Lap("10", "pikk"),
                new Lap("J", "pikk"),
                new Lap("Q", "pikk"),
                new Lap("K", "pikk"),
                new Lap("A", "pikk")
        );
        assertTrue(ertekeles.isRoyalFlush(lapok));
    }

    @Test
    void testIsStraightFlush() {
        List<Lap> lapok = Arrays.asList(
                new Lap("2", "pikk"),
                new Lap("3", "pikk"),
                new Lap("4", "pikk"),
                new Lap("5", "pikk"),
                new Lap("6", "pikk")
        );
        assertTrue(ertekeles.isStraightFlush(lapok));
    }

    @Test
    void testIsFourOfAKind() {
        List<Lap> lapok = Arrays.asList(
                new Lap("5", "pikk"),
                new Lap("5", "treff"),
                new Lap("5", "karo"),
                new Lap("5", "kor"),
                new Lap("10", "pikk")
        );
        assertTrue(ertekeles.isPairType(lapok, 4));
    }

    @Test
    void testIsFullHouse() {
        List<Lap> lapok = Arrays.asList(
                new Lap("3", "pikk"),
                new Lap("3", "treff"),
                new Lap("3", "karo"),
                new Lap("6", "kor"),
                new Lap("6", "pikk")
        );
        assertTrue(ertekeles.isFullHouse(lapok));
    }

    @Test
    void testIsFlush() {
        List<Lap> lapok = Arrays.asList(
                new Lap("2", "pikk"),
                new Lap("7", "pikk"),
                new Lap("9", "pikk"),
                new Lap("J", "pikk"),
                new Lap("Q", "pikk")
        );
        assertTrue(ertekeles.isFlush(lapok));
    }

    @Test
    void testIsStraight() {
        List<Lap> lapok = Arrays.asList(
                new Lap("3", "karo"),
                new Lap("4", "treff"),
                new Lap("5", "pikk"),
                new Lap("6", "kor"),
                new Lap("7", "pikk")
        );
        assertTrue(ertekeles.isStraight(lapok));
    }

    @Test
    void testIsTwoPair() {
        List<Lap> lapok = Arrays.asList(
                new Lap("4", "pikk"),
                new Lap("4", "karo"),
                new Lap("9", "treff"),
                new Lap("9", "kor"),
                new Lap("A", "pikk")
        );
        assertTrue(ertekeles.isTwoPair(lapok));
    }

    @Test
    void testIsThreeOfAKind() {
        List<Lap> lapok = Arrays.asList(
                new Lap("8", "pikk"),
                new Lap("8", "karo"),
                new Lap("8", "kor"),
                new Lap("2", "treff"),
                new Lap("K", "pikk")
        );
        assertTrue(ertekeles.isPairType(lapok, 3));
    }

    @Test
    void testIsPair() {
        List<Lap> lapok = Arrays.asList(
                new Lap("J", "pikk"),
                new Lap("J", "karo"),
                new Lap("4", "treff"),
                new Lap("8", "kor"),
                new Lap("A", "pikk")
        );
        assertTrue(ertekeles.isPairType(lapok, 2));
    }
}
