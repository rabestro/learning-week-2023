package copilot.roman

import spock.lang.*

@Title('Roman to Arabic converter')
@Narrative("""
As a mathematician
I want to convert roman numerals to arabic numbers
So that I can do arithmetic with them
""")
@Issue('1')
@See('https://en.wikipedia.org/wiki/Roman_numerals')
class RomanToArabicConverterTest extends Specification {

    @PendingFeature
    @Unroll('convert #roman to #arabic')
    def 'convert roman numeral to arabic number'() {
        given:
        def converter = new RomanToArabicConverter()

        expect:
        converter.applyAsInt(roman) == arabic

        where:
        roman       | arabic
        'I'         | 1
        'II'        | 2
        'III'       | 3
        'IV'        | 4
        'V'         | 5
        'VI'        | 6
        'VII'       | 7
        'VIII'      | 8
        'IX'        | 9
        'X'         | 10
        'XI'        | 11
        'XL'        | 40
        'L'         | 50
        'LX'        | 60
        'XC'        | 90
        'C'         | 100
        'CX'        | 110
        'CD'        | 400
        'D'         | 500
        'DC'        | 600
        'CM'        | 900
        'M'         | 1000
        'MCMXCIX'   | 1999
        'MMXXI'     | 2021
        'MMMCMXCIX' | 3999
    }

    @PendingFeature
    def 'result is undefined for invalid Roman numerals'() {
        given:
        def converter = new RomanToArabicConverter()

        when:
        converter.applyAsInt(invalidRomanNumeral)

        then:
        notThrown IllegalArgumentException

        where:
        invalidRomanNumeral << [
            'IIII', 'VV', 'XXXX', 'LL', 'CCCC', 'DD', 'MMMM',
            'AB', '478', '#$%', 'MCMXCIIX', 'MCMXCIIX', 'MCMXCIIX'
        ]
    }
}
