package epam.flipflop;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * A predicate that emulates flip-flop logic similar to the two-dots flip-flop in Perl or Ruby.
 * The predicate takes two input predicates, a start condition, and an end condition.
 * When the start condition is met, the predicate returns true for all subsequent inputs
 * until the end condition is met, at which point it returns true and resets its internal state.
 *
 * @param <T> The type of the input to the predicate
 */
public class FlipFlopPredicate<T> implements Predicate<T> {
    private final Predicate<? super T> startPredicate;
    private final Predicate<? super T> endPredicate;
    private boolean state;

    /**
     * Constructs a new FlipFlopPredicate with the given start and end conditions.
     *
     * @param startCondition The predicate that represents the start condition
     * @param endCondition   The predicate that represents the end condition
     */
    public FlipFlopPredicate(Predicate<T> startCondition, Predicate<T> endCondition) {
        this.startPredicate = requireNonNull(startCondition);
        this.endPredicate = requireNonNull(endCondition);
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value The input argument
     * @return {@code true} if the input matches the flip-flop logic, otherwise {@code false}
     */
    @Override
    public boolean test(final T value) {
        var result = state || startPredicate.test(value);
        state = result && !endPredicate.test(value);
        return result;
    }
}
