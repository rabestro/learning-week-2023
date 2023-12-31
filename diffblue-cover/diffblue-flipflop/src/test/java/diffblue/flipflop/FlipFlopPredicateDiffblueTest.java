package diffblue.flipflop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FlipFlopPredicateDiffblueTest {
    /**
     * Method under test:
     * {@link FlipFlopPredicate#FlipFlopPredicate(Predicate, Predicate)}
     */
    @Test
    void testConstructor() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(true);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(true);

        // Act
        FlipFlopPredicate<Object> actualFlipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        String string = "Value";
        boolean actualTestResult = actualFlipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        verify(rhs).test(Mockito.<Object>any());
        assertTrue(actualTestResult);
    }

    /**
     * Method under test:
     * {@link FlipFlopPredicate#FlipFlopPredicate(Predicate, Predicate)}
     */
    @Test
    void testConstructor2() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(false);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(true);

        // Act
        FlipFlopPredicate<Object> actualFlipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        String string = "Value";
        boolean actualTestResult = actualFlipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        assertFalse(actualTestResult);
    }

    /**
     * Method under test:
     * {@link FlipFlopPredicate#FlipFlopPredicate(Predicate, Predicate)}
     */
    @Test
    void testConstructor3() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(true);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(false);

        // Act
        FlipFlopPredicate<Object> actualFlipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        String string = "Value";
        boolean actualTestResult = actualFlipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        verify(rhs).test(Mockito.<Object>any());
        assertTrue(actualTestResult);
    }

    /**
     * Method under test: {@link FlipFlopPredicate#test(Object)}
     */
    @Test
    void testTest() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(true);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(true);
        FlipFlopPredicate<Object> flipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        String string = "Value";

        // Act
        boolean actualTestResult = flipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        verify(rhs).test(Mockito.<Object>any());
        assertTrue(actualTestResult);
    }

    /**
     * Method under test: {@link FlipFlopPredicate#test(Object)}
     */
    @Test
    void testTest2() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(false);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(true);
        FlipFlopPredicate<Object> flipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        String string = "Value";

        // Act
        boolean actualTestResult = flipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        assertFalse(actualTestResult);
    }

    /**
     * Method under test: {@link FlipFlopPredicate#test(Object)}
     */
    @Test
    void testTest3() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(true);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(false);
        FlipFlopPredicate<Object> flipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        String string = "Value";

        // Act
        boolean actualTestResult = flipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        verify(rhs).test(Mockito.<Object>any());
        assertTrue(actualTestResult);
    }

    /**
     * Method under test: {@link FlipFlopPredicate#test(Object)}
     */
    @Test
    void testTest4() {
        // Arrange
        Predicate<Object> lhs = mock(Predicate.class);
        when(lhs.test(Mockito.<Object>any())).thenReturn(true);
        Predicate<Object> rhs = mock(Predicate.class);
        when(rhs.test(Mockito.<Object>any())).thenReturn(false);

        FlipFlopPredicate<Object> flipFlopPredicate = new FlipFlopPredicate<>(lhs, rhs);
        flipFlopPredicate.test(0);
        String string = "42";

        // Act
        boolean actualTestResult = flipFlopPredicate.test(string);

        // Assert
        verify(lhs).test(Mockito.<Object>any());
        verify(rhs, atLeast(1)).test(Mockito.<Object>any());
        assertTrue(actualTestResult);
    }
}
