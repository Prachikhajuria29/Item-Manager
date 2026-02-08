package org.e_commerce.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KMPSearchTest {

    @Test
    void contains_exactMatch_shouldReturnTrue() {
        assertTrue(KMPSearch.contains("hello world", "world"));
        assertTrue(KMPSearch.contains("hello world", "hello"));
        assertTrue(KMPSearch.contains("hello world", "hello world"));
    }

    @Test
    void contains_partialMatch_shouldReturnTrue() {
        assertTrue(KMPSearch.contains("Spring Boot Application", "Boot"));
        assertTrue(KMPSearch.contains("Spring Boot Application", "App"));
        assertTrue(KMPSearch.contains("Spring Boot Application", "pring"));
    }

    @Test
    void contains_caseInsensitive_shouldReturnTrue() {
        assertTrue(KMPSearch.contains("Hello World", "WORLD"));
        assertTrue(KMPSearch.contains("HELLO WORLD", "world"));
        assertTrue(KMPSearch.contains("HeLLo WoRLd", "hello"));
    }

    @Test
    void contains_noMatch_shouldReturnFalse() {
        assertFalse(KMPSearch.contains("hello world", "xyz"));
        assertFalse(KMPSearch.contains("Spring Boot", "Django"));
        assertFalse(KMPSearch.contains("test", "testing"));
    }

    @Test
    void contains_nullText_shouldReturnFalse() {
        assertFalse(KMPSearch.contains(null, "pattern"));
    }

    @Test
    void contains_nullPattern_shouldReturnFalse() {
        assertFalse(KMPSearch.contains("text", null));
    }

    @Test
    void contains_emptyPattern_shouldReturnFalse() {
        assertFalse(KMPSearch.contains("text", ""));
    }

    @Test
    void contains_emptyText_shouldReturnFalse() {
        assertFalse(KMPSearch.contains("", "pattern"));
    }

    @Test
    void contains_patternLongerThanText_shouldReturnFalse() {
        assertFalse(KMPSearch.contains("hi", "hello"));
    }

    @Test
    void contains_specialCharacters_shouldWork() {
        assertTrue(KMPSearch.contains("user@example.com", "@example"));
        assertTrue(KMPSearch.contains("path/to/file.txt", "/to/"));
        assertTrue(KMPSearch.contains("price: $99.99", "$99"));
    }

    @Test
    void contains_repeatingPatterns_shouldWork() {
        assertTrue(KMPSearch.contains("aaabaaabaaab", "aaab"));
        assertTrue(KMPSearch.contains("abcabcabc", "cabc"));
    }

    @Test
    void contains_singleCharacter_shouldWork() {
        assertTrue(KMPSearch.contains("hello", "e"));
        assertTrue(KMPSearch.contains("hello", "h"));
        assertTrue(KMPSearch.contains("hello", "o"));
    }
}
