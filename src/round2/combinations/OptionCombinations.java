package round2.combinations;

import java.math.BigInteger;

public class OptionCombinations {
    // Always trust math (A friendly reminder to always simplify mathematical expressions)
    // \sum^n_{p=2} C^p_n = 2^n - n - 1
    public String countCombinations(int strikes, int expiries) {
        int n = 2 * strikes * expiries;

        return BigInteger.TWO.pow(n).subtract(BigInteger.valueOf(n + 1)).toString();
    }
}
