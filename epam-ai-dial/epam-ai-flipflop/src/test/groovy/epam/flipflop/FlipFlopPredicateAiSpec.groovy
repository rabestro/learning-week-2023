package epam.flipflop

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

@Title('Flip-flop predicate')
class FlipFlopPredicateAiSpec extends Specification {

    @Subject
    FlipFlopPredicate<Object> flipFlopPredicate

    @Unroll
    def 'flip-flop with startPredicate=#startPredicate, endPredicate=#endPredicate, initialState=#initialState, finalState=#finalState'() {
        given:
        flipFlopPredicate = new FlipFlopPredicate<>({ _ -> startPredicate }, { _ -> endPredicate })
        and:
        flipFlopPredicate.state = initialState

        when:
        boolean result = flipFlopPredicate.test(_)

        then:
        result == expectedResult
        and:
        flipFlopPredicate.state == finalState

        where:
        startPredicate | endPredicate | initialState || finalState | expectedResult
        true           | true         | false        || false      | true
        true           | false        | false        || true       | true
        false          | true         | false        || false      | false
        false          | false        | false        || false      | false
        true           | true         | true         || false      | true
        true           | false        | true         || true       | true
        false          | true         | true         || false      | true
        false          | false        | true         || true       | true
    }
}
