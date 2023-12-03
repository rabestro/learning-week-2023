package epam.legalage

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.Clock
import java.time.LocalDate
import java.time.ZoneId

class LegalAgePredicateSpec extends Specification {

    @Unroll('#scenario')
    def 'determine whether the user has reached legal age'() {
        given:
        def clock = Clock.fixed(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault())
        @Subject
        def legalAgePredicate = new LegalAgePredicate(clock)

        expect:
        legalAgePredicate.test(dateOfBirth) == expectedResult

        where:
        scenario                          | dateOfBirthStr | currentDateStr | expectedResult
        'User is exactly 18 years old'    | '2004-03-01'   | '2022-03-01'   | true
        'User is one day older than 18'   | '2004-02-28'   | '2022-03-01'   | true
        'User is one day younger than 18' | '2004-03-02'   | '2022-03-01'   | false
        'User is exactly 19 years old'    | '2003-03-01'   | '2022-03-01'   | true
        'User is born today (edge case)'  | '2022-03-01'   | '2022-03-01'   | false
        'User is exactly 17 years old'    | '2005-03-01'   | '2022-03-01'   | false
        'User is exactly 20 years old'    | '2002-03-01'   | '2022-03-01'   | true
        'User is born on a leap year'     | '2000-02-29'   | '2022-03-01'   | true

        and:
        dateOfBirth = LocalDate.parse(dateOfBirthStr)
        currentDate = LocalDate.parse(currentDateStr)
    }

    def 'the constructor sets the system clock by default'() {
        given:
        def legalAgePredicate = new LegalAgePredicate()

        expect:
        legalAgePredicate.clock == Clock.systemDefaultZone()
    }
}
