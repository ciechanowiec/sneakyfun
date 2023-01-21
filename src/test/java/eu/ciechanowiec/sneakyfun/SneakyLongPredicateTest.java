package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongPredicate.sneaky;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SneakyLongPredicateTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongPredicate<?> longPredicate = input -> input % 2 == 0;
        boolean result = longPredicate.test(10);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongPredicate<?> longPredicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longPredicate.test(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongPredicate<?> longPredicate = input -> input % 2 == 0;
        boolean result = sneaky(longPredicate).test(10);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongPredicate<?> longPredicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longPredicate).test(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
