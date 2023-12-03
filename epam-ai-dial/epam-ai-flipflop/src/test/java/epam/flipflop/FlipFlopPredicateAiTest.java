package epam.flipflop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FlipFlopPredicate Test")
class FlipFlopPredicateAiTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
            new TestCase("Single input, doesn't meet start condition",
                List.of(1), x -> x == 2, x -> x == 3, List.of(false)),
            new TestCase("Single input, meets start condition but not end condition",
                List.of(2), x -> x == 2, x -> x == 3, List.of(true)),
            new TestCase("Single input, meets both start and end conditions",
                List.of(2), x -> x == 2, x -> x == 2, List.of(true)),
            new TestCase("Multiple inputs, start condition met, no end condition met",
                List.of(1, 2, 4), x -> x == 2, x -> x == 3, List.of(false, true, true)),
            new TestCase("Multiple inputs, start condition met, end condition met",
                List.of(1, 2, 3, 4), x -> x == 2, x -> x == 3, List.of(false, true, true, false)),
            new TestCase("Multiple inputs, start and end conditions met multiple times",
                List.of(1, 2, 3, 4, 2, 4, 3), x -> x == 2, x -> x == 3,
                List.of(false, true, true, false, true, true, true))
        );
    }

    @DisplayName("Test FlipFlopPredicate")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("testCases")
    void testFlipFlopPredicate(TestCase testCase) {
        var flipFlopPredicate = new FlipFlopPredicate<>(testCase.startCondition, testCase.endCondition);
        var results = testCase.inputs.stream().map(flipFlopPredicate::test).toList();

        assertThat(results)
            .as("Expected results for the given inputs")
            .isEqualTo(testCase.expectedResults);
    }

    record TestCase(
        String description,
        List<Integer> inputs,
        Predicate<Integer> startCondition,
        Predicate<Integer> endCondition,
        List<Boolean> expectedResults
    ) {
        @Override
        public String toString() {
            return description;
        }
    }
}
