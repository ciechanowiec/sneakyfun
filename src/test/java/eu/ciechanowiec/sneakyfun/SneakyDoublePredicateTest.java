package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoublePredicate.sneaky;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SneakyDoublePredicateTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoublePredicate<?> doublePredicate = input -> ((int) input) % 2 == 0;
        boolean result = doublePredicate.test(4.5);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoublePredicate<?> doublePredicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doublePredicate.test(3.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoublePredicate<?> doublePredicate = input -> ((int) input) % 2 == 0;
        boolean result = sneaky(doublePredicate).test(4.5);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoublePredicate<?> doublePredicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doublePredicate).test(3.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
