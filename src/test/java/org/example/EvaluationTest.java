package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class EvaluationTest {

    private Evaluation evaluation;

    @BeforeEach
    void setUp() {
        evaluation = new Evaluation();
    }

    @Test
    void testIsRoyalFlush() {
        List<Card> lapok = Arrays.asList(
                new Card("10", "pikk"),
                new Card("J", "pikk"),
                new Card("Q", "pikk"),
                new Card("K", "pikk"),
                new Card("A", "pikk")
        );
        assertTrue(evaluation.isRoyalFlush(lapok));
    }

    @Test
    void testIsStraightFlush() {
        List<Card> lapok = Arrays.asList(
                new Card("2", "pikk"),
                new Card("3", "pikk"),
                new Card("4", "pikk"),
                new Card("5", "pikk"),
                new Card("6", "pikk")
        );
        assertTrue(evaluation.isStraightFlush(lapok));
    }

    @Test
    void testIsFourOfAKind() {
        List<Card> lapok = Arrays.asList(
                new Card("5", "pikk"),
                new Card("5", "treff"),
                new Card("5", "karo"),
                new Card("5", "kor"),
                new Card("10", "pikk")
        );
        assertTrue(evaluation.isPairType(lapok, 4));
    }

    @Test
    void testIsFullHouse() {
        List<Card> lapok = Arrays.asList(
                new Card("3", "pikk"),
                new Card("3", "treff"),
                new Card("3", "karo"),
                new Card("6", "kor"),
                new Card("6", "pikk")
        );
        assertTrue(evaluation.isFullHouse(lapok));
    }

    @Test
    void testIsFlush() {
        List<Card> lapok = Arrays.asList(
                new Card("2", "pikk"),
                new Card("7", "pikk"),
                new Card("9", "pikk"),
                new Card("J", "pikk"),
                new Card("Q", "pikk")
        );
        assertTrue(evaluation.isFlush(lapok));
    }

    @Test
    void testIsStraight() {
        List<Card> lapok = Arrays.asList(
                new Card("3", "karo"),
                new Card("4", "treff"),
                new Card("5", "pikk"),
                new Card("6", "kor"),
                new Card("7", "pikk")
        );
        assertTrue(evaluation.isStraight(lapok));
    }

    @Test
    void testIsTwoPair() {
        List<Card> lapok = Arrays.asList(
                new Card("4", "pikk"),
                new Card("4", "karo"),
                new Card("9", "treff"),
                new Card("9", "kor"),
                new Card("A", "pikk")
        );
        assertTrue(evaluation.isTwoPair(lapok));
    }

    @Test
    void testIsThreeOfAKind() {
        List<Card> lapok = Arrays.asList(
                new Card("8", "pikk"),
                new Card("8", "karo"),
                new Card("8", "kor"),
                new Card("2", "treff"),
                new Card("K", "pikk")
        );
        assertTrue(evaluation.isPairType(lapok, 3));
    }

    @Test
    void testIsPair() {
        List<Card> lapok = Arrays.asList(
                new Card("J", "pikk"),
                new Card("J", "karo"),
                new Card("4", "treff"),
                new Card("8", "kor"),
                new Card("A", "pikk")
        );
        assertTrue(evaluation.isPairType(lapok, 2));
    }
}
