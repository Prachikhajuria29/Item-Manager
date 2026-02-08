package org.e_commerce.util;

/**
 * KMP (Knuth-Morris-Pratt) String Matching Algorithm
 * Provides efficient substring pattern matching with O(n+m) complexity
 */
public class KMPSearch {

    /**
     * Searches for pattern in text using KMP algorithm (case-insensitive)
     *
     * @param text The text to search in
     * @param pattern The pattern to search for
     * @return true if pattern is found in text, false otherwise
     */
    public static boolean contains(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty()) {
            return false;
        }

        // Convert to lowercase for case-insensitive search
        String lowerText = text.toLowerCase();
        String lowerPattern = pattern.toLowerCase();

        return kmpSearch(lowerText, lowerPattern);
    }

    /**
     * Core KMP search implementation
     *
     * @param text The text to search in
     * @param pattern The pattern to search for
     * @return true if pattern is found, false otherwise
     */
    private static boolean kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return false;
        }

        if (m > n) {
            return false;
        }

        // Build LPS (Longest Proper Prefix which is also Suffix) array
        int[] lps = computeLPSArray(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                // Pattern found
                return true;
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // Mismatch after j matches
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }

    /**
     * Computes the LPS (Longest Proper Prefix which is also Suffix) array
     * This array is used to skip characters during pattern matching
     *
     * @param pattern The pattern to compute LPS for
     * @return The LPS array
     */
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // length of the previous longest prefix suffix
        int i = 1;

        lps[0] = 0; // lps[0] is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
