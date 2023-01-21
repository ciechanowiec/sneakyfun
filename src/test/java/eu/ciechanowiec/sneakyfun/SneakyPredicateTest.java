package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyPredicate.sneaky;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SneakyPredicateTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyPredicate<Integer, ?> predicate = input -> input % 2 == 0;
        boolean result = predicate.test(10);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyPredicate<Integer, ?> predicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> predicate.test(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyPredicate<Integer, ?> predicate = input -> input % 2 == 0;
        boolean result = sneaky(predicate).test(10);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyPredicate<Integer, ?> predicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(predicate).test(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
