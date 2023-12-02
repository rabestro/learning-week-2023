package diffblue.legalage;

import java.time.LocalDate;
import java.util.function.Predicate;

public class LegalAgePredicate implements Predicate<LocalDate> {

    @Override
    public boolean test(LocalDate birthday) {
        int yearOfBirth = birthday.getYear();
        int yearNow = LocalDate.now().getYear();
        return yearNow - yearOfBirth >= 18;
    }
}
