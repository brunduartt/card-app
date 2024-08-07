package org.bancodobrasil.core.cardpayment.utils;

public class ValidationUtils {
    /**
     * Validates if card number is valid
     * @param cc Credit card number
     * @return true if valid and false if not valid
     */
    public static boolean isCreditCardNumberValid(String cc) {
        if (! cc.matches("(?=[456]|37)[0-9]{13,16}"))
            return false;
        int sum = 0;
        for (int i = cc.length() - 1, pos = 1; i >= 0; i--, pos++) {
            int digit = cc.charAt(i) - '0';
            sum += (pos % 2 == 1 ? digit : digit < 5 ? digit * 2 : digit * 2 - 9);
        }
        return (sum % 10 == 0);
    }
}
