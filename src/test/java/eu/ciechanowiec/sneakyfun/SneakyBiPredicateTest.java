package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyBiPredicate.sneaky;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SneakyBiPredicateTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyBiPredicate<Integer, Integer, ?> biPredicate = (inputOne, inputTwo) -> inputOne + inputTwo == 15;
        boolean result = biPredicate.test(10, 5);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyBiPredicate<Integer, Integer, ?> biPredicate = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> biPredicate.test(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyBiPredicate<Integer, Integer, ?> biPredicate = (inputOne, inputTwo) -> inputOne + inputTwo == 15;
        boolean result = sneaky(biPredicate).test(10, 5);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyBiPredicate<Integer, Integer, ?> biPredicate = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(biPredicate).test(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
