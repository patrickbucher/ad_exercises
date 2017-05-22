package ch.hslu.ad.sw12.ex02;

public class StateSearch {

    public static int stateSearchANANAS(final String a) {
        int i = 0;
        String state = "";
        char[] c = a.toCharArray();
        do {
            switch (state) {
            case "":
                if (c[i] == 'A') {
                    state = "A";
                } else {
                    state = "";
                }
                break;
            case "A":
                if (c[i] == 'N') {
                    state = "AN";
                } else if (c[i] == 'A') {
                    state = "A";
                } else {
                    state = "";
                }
                break;
            case "AN":
                if (c[i] == 'A') {
                    state = "ANA";
                } else {
                    state = "";
                }
                break;
            case "ANA":
                if (c[i] == 'N') {
                    state = "ANAN";
                } else if (c[i] == 'A') {
                    state = "A";
                } else {
                    state = "";
                }
                break;
            case "ANAN":
                if (c[i] == 'A') {
                    state = "ANANA";
                } else {
                    state = "";
                }
                break;
            case "ANANA":
                if (c[i] == 'S') {
                    state = "ANANAS";
                } else if (c[i] == 'N') {
                    state = "ANAN";
                } else if (c[i] == 'A') {
                    state = "A";
                }
                break;
            }
            i++;
        } while (!state.equals("ANANAS") && i < a.length());
        if (state.equals("ANANAS")) {
            return i - "ANANAS".length();
        }
        return -1;
    }
}
