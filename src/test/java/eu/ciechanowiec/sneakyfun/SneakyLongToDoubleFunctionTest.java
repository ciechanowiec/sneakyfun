package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongToDoubleFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongToDoubleFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongToDoubleFunction<?> longToDoubleFunction = input -> (double) input / 2;
        double result = longToDoubleFunction.applyAsDouble(5);
        assertEquals(2.5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongToDoubleFunction<?> longToDoubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longToDoubleFunction.applyAsDouble(5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongToDoubleFunction<?> longToDoubleFunction = input -> (double) input / 2;
        double result = sneaky(longToDoubleFunction).applyAsDouble(5);
        assertEquals(2.5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongToDoubleFunction<?> longToDoubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longToDoubleFunction).applyAsDouble(5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
