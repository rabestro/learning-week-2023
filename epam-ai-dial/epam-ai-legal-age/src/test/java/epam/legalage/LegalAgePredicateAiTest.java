package epam.legalage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LegalAgePredicate tests")
class LegalAgePredicateAiTest {

    private static Stream<Arguments> legalAgePredicateTestCases() {
        return Stream.of(
                Arguments.of("User is exactly 18 years old", "2004-03-01", "2022-03-01", true),
                Arguments.of("User is one day older than 18", "2004-02-28", "2022-03-01", true),
                Arguments.of("User is one day younger than 18", "2004-03-02", "2022-03-01", false),
                Arguments.of("User is exactly 19 years old", "2003-03-01", "2022-03-01", true),
                Arguments.of("User is born today (edge case)", "2022-03-01", "2022-03-01", false)
        );
    }

    @DisplayName("LegalAgePredicate test cases")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("legalAgePredicateTestCases")
    void legalAgePredicateTest(String description, String dateOfBirthStr, String currentDateStr, boolean expectedResult) {
        var dateOfBirth = LocalDate.parse(dateOfBirthStr);
        var currentDate = LocalDate.parse(currentDateStr);
        var clock = Clock.fixed(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        var legalAgePredicate = new LegalAgePredicate(clock);

        assertThat(legalAgePredicate.test(dateOfBirth))
                .as("Check if user with date of birth %s is over 18 on %s", dateOfBirth, currentDate)
                .isEqualTo(expectedResult);
    }
}
