package epam.roman;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("RomanToArabicConverter Test")
class RomanToArabicConverterAiTest {

    private final RomanToArabicConverter converter = new RomanToArabicConverter();

    @DisplayName("Roman numeral to integer conversion:")
    @ParameterizedTest(name = "[{index}] {0}: {1} -> {2}")
    @CsvSource(delimiter = '|', textBlock = """
            Minimum value                   | I        | 1
            Only one type of symbol         | III      | 3
            Subtraction case (IV)           | IV       | 4
            Subtraction case (IX)           | IX       | 9
            Subtraction case (XL)           | XL       | 40
            Subtraction case (XC)           | XC       | 90
            Subtraction case (CD)           | CD       | 400
            Subtraction case (CM)           | CM       | 900
            Combination of symbols          | LVIII    | 58
            Multiple subtraction cases      | MCMXCIV  | 1994
            Maximum value                   | MMMCMXCIX| 3999
            All symbols in descending order | MDCLXVI  | 1666
        """)
    void romanToIntegerConversionTest(String description, String input, int expectedOutput) {
        int result = converter.applyAsInt(input);
        assertThat(result)
            .as("Roman numeral %s should be converted to %d", input, expectedOutput)
            .isEqualTo(expectedOutput);
    }
}
