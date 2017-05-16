package ch.hslu.ad.sw11;

public class DeterministicFiniteAutomaton {

    /**
     * <ul>
     * <li>start with a 0
     * <li>alternating groups of 1s and 0
     * <li>0 always alone
     * <li>1 always in groups
     * <li>end with a 0
     * </ul>
     */
    public static boolean isWordLanguage(final String str) {
        String state = "z0";
        for (char c : str.toCharArray()) {
            switch (c) {
            case '0':
                switch (state) {
                case "z0":
                    state = "z1";
                    break;
                case "z2":
                    state = "z4";
                    break;
                default:
                    return false;
                }
                break;
            case '1':
                switch (state) {
                case "z1":
                    state = "z2";
                    break;
                case "z2":
                    state = "z3";
                    break;
                case "z3":
                    state = "z2";
                    break;
                case "z4":
                    state = "z2";
                    break;
                default:
                    return false;
                }
                break;
            default:
                return false;
            }
        }
        return "z4".equals(state) || "z1".equals(state);
    }
}
