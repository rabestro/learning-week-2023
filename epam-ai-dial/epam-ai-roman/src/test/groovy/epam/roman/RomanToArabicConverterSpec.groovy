package epam.roman;

import spock.lang.Specification
import spock.lang.Unroll

class RomanToArabicConverterSpec extends Specification {

    @Unroll('#description')
    def 'Roman numeral to integer conversion'() {
        given:
        def converter = new RomanToArabicConverter()

        expect:
        converter.applyAsInt(input) == expectedOutput

        where:
        description                       | input       | expectedOutput
        'Minimum value'                   | 'I'         | 1
        'Only one type of symbol'         | 'III'       | 3
        'Subtraction case (IV)'           | 'IV'        | 4
        'Subtraction case (IX)'           | 'IX'        | 9
        'Subtraction case (XL)'           | 'XL'        | 40
        'Subtraction case (XC)'           | 'XC'        | 90
        'Subtraction case (CD)'           | 'CD'        | 400
        'Subtraction case (CM)'           | 'CM'        | 900
        'Combination of symbols'          | 'LVIII'     | 58
        'Multiple subtraction cases'      | 'MCMXCIV'   | 1994
        'Maximum value'                   | 'MMMCMXCIX' | 3999
        'All symbols in descending order' | 'MDCLXVI'   | 1666
    }
}
