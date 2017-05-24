package ch.hslu.ad.sw12.ex05;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class QuickSearch {

    public static int quickSearch(String a, String p) {
        int n = a.length();
        int m = p.length();

        Set<Character> alphabetSet = determineAlphabet(a);
        Character[] alphabet = alphabetSet.toArray(new Character[alphabetSet.size()]);
        char first = alphabet[0];
        char last = alphabet[alphabet.length - 1];
        Map<Character, Integer> shift = buildShiftMap(first, last, p);

        // search
        int i = 0;
        int j = 0;
        do {
            if (a.charAt(i + j) == p.charAt(j)) {
                j++;
            } else {
                if (i + m < n) {
                    i += shift.get(a.charAt(i + m));
                    j = 0;
                } else {
                    break;
                }
            }
        } while (j < m && i + m <= n);

        if (j == m) {
            return i; // pattern found, starting at i
        } else {
            return -1; // pattern not found
        }
    }

    private static Set<Character> determineAlphabet(String a) {
        if (a == null || a.length() == 0) {
            return Collections.emptySet();
        }

        Set<Character> alphabet = new TreeSet<>();
        for (char c : a.toCharArray()) {
            alphabet.add(c);
        }
        return alphabet;
    }

    private static Map<Character, Integer> buildShiftMap(char from, char to, String p) {
        if (from >= to) {
            return Collections.emptyMap();
        }

        Map<Character, Integer> shiftMap = new TreeMap<>();
        int m = p.length();
        for (char c = from; c <= to; c++) {
            shiftMap.put(c, m);
        }
        for (int i = 0; i < m; i++) {
            shiftMap.put(p.charAt(i), m - i);
        }
        return shiftMap;
    }

}
