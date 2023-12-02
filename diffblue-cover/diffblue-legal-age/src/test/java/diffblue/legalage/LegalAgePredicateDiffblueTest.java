package diffblue.legalage;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class LegalAgePredicateDiffblueTest {
    /**
     * Method under test: {@link LegalAgePredicate#test(LocalDate)}
     */
    @Test
    void testTest() {
        // Arrange
        LegalAgePredicate legalAgePredicate = new LegalAgePredicate();
        LocalDate birthday = LocalDate.now();

        // Act
        boolean actualTestResult = legalAgePredicate.test(birthday);

        // Assert
        assertFalse(actualTestResult);
    }
}
