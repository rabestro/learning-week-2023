package epam.legalage;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;

/**
 * A predicate that checks if a user is over 18 years old based on their date of birth.
 */
public final class LegalAgePredicate implements Predicate<LocalDate> {

    private static final int LEGAL_AGE = 18;
    private final Clock clock;

    /**
     * Creates a LegalAgePredicate with the system default clock.
     */
    public LegalAgePredicate() {
        this(Clock.systemDefaultZone());
    }

    /**
     * Creates a LegalAgePredicate with the specified clock.
     *
     * @param clock the clock to use for calculating the current date
     */
    public LegalAgePredicate(Clock clock) {
        this.clock = clock;
    }

    /**
     * Tests if the user with the given date of birth is over 18 years old.
     *
     * @param dateOfBirth the user's date of birth
     * @return true if the user is over 18 years old, false otherwise
     */
    @Override
    public boolean test(LocalDate dateOfBirth) {
        var currentDate = LocalDate.now(clock);
        var age = Period.between(dateOfBirth, currentDate).getYears();
        return age >= LEGAL_AGE;
    }
}
