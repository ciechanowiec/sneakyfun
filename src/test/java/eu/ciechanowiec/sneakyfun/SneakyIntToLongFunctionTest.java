package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntToLongFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntToLongFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntToLongFunction<?> intToLongFunction = input -> input + 15L;
        long result = intToLongFunction.applyAsLong(10);
        assertEquals(25, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntToLongFunction<?> intToLongFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intToLongFunction.applyAsLong(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntToLongFunction<?> intToLongFunction = input -> input + 15L;
        long result = sneaky(intToLongFunction).applyAsLong(10);
        assertEquals(25, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntToLongFunction<?> intToLongFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intToLongFunction).applyAsLong(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
