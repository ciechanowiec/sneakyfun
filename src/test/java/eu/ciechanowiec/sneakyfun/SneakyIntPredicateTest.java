package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntPredicate.sneaky;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SneakyIntPredicateTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntPredicate<?> intPredicate = input -> input % 2 == 0;
        boolean result = intPredicate.test(10);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntPredicate<?> intPredicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intPredicate.test(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntPredicate<?> intPredicate = input -> input % 2 == 0;
        boolean result = sneaky(intPredicate).test(10);
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntPredicate<?> intPredicate = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intPredicate).test(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
