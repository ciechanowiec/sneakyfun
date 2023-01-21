package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleToLongFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleToLongFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoubleToLongFunction<?> doubleToLongFunction = input -> (long) input;
        long result = doubleToLongFunction.applyAsLong(3.5);
        assertEquals(3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleToLongFunction<?> doubleToLongFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doubleToLongFunction.applyAsLong(3.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoubleToLongFunction<?> doubleToLongFunction = input -> (long) input;
        long result = sneaky(doubleToLongFunction).applyAsLong(3.5);
        assertEquals(3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleToLongFunction<?> doubleToLongFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleToLongFunction).applyAsLong(3.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
