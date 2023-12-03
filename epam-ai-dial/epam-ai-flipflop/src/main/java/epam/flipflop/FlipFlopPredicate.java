package epam.flipflop;

import java.util.function.Predicate;

/**
 * A predicate that emulates flip-flop logic similar to the two-dots flip-flop in Perl or Ruby.
 * The predicate takes two input predicates, a start condition, and an end condition.
 * When the start condition is met, the predicate returns true for all subsequent inputs
 * until the end condition is met, at which point it returns true and resets its internal state.
 *
 * @param <T> The type of the input to the predicate
 */
public class FlipFlopPredicate<T> implements Predicate<T> {
    private final Predicate<T> startCondition;
    private final Predicate<T> endCondition;
    private boolean active;

    /**
     * Constructs a new FlipFlopPredicate with the given start and end conditions.
     *
     * @param startCondition The predicate that represents the start condition
     * @param endCondition   The predicate that represents the end condition
     */
    public FlipFlopPredicate(Predicate<T> startCondition, Predicate<T> endCondition) {
        this.startCondition = startCondition;
        this.endCondition = endCondition;
        this.active = false;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t The input argument
     * @return {@code true} if the input matches the flip-flop logic, otherwise {@code false}
     */
    @Override
    public boolean test(T t) {
        if (active) {
            if (endCondition.test(t)) {
                active = false;
            }
            return true;
        } else if (startCondition.test(t)) {
            active = true;
            return test(t);
        }
        return false;
    }
}
