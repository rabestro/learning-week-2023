package epam.roman;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

/**
 * A converter that converts Roman numerals to Arabic integers.
 */
public final class RomanToArabicConverter implements ToIntFunction<String> {

    private static final Map<Character, Integer> ROMAN_TO_ARABIC_MAP = new HashMap<>();

    static {
        ROMAN_TO_ARABIC_MAP.put('I', 1);
        ROMAN_TO_ARABIC_MAP.put('V', 5);
        ROMAN_TO_ARABIC_MAP.put('X', 10);
        ROMAN_TO_ARABIC_MAP.put('L', 50);
        ROMAN_TO_ARABIC_MAP.put('C', 100);
        ROMAN_TO_ARABIC_MAP.put('D', 500);
        ROMAN_TO_ARABIC_MAP.put('M', 1000);
    }

    /**
     * Converts a Roman numeral string to an Arabic integer.
     *
     * @param roman the Roman numeral string to be converted
     * @return the Arabic integer representation of the Roman numeral
     */
    @Override
    public int applyAsInt(String roman) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = ROMAN_TO_ARABIC_MAP.get(currentChar);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;
    }
}
