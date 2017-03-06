package ch.hslu.ad.sw01.permutation;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
        List<String> permutations = permute(new char[] { 'A', 'B', 'C', 'D' });
        for (String permutation : permutations) {
            System.out.println(permutation);
        }
    }

    private static List<String> permute(char chars[]) {
        List<String> permutations = new ArrayList<>();
        if (chars.length == 1) {
            permutations.add(String.valueOf(chars[0]));
        } else {
            for (int i = 0; i < chars.length; i++) {
                char remainder[] = exclude(chars, i);
                List<String> remainderPermutations = permute(remainder);
                for (String remainderPermutation : remainderPermutations) {
                    permutations.add(chars[i] + remainderPermutation);
                }
            }
        }
        return permutations;
    }

    private static char[] exclude(char[] chars, int n) {
        char charsExcludedN[] = new char[chars.length - 1];
        int r, w = 0;
        for (r = 0; r < chars.length; r++) {
            if (r != n) {
                charsExcludedN[w++] = chars[r];
            }
        }
        return charsExcludedN;
    }
}
