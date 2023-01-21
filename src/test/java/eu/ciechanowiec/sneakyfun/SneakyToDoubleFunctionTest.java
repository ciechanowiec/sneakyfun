package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyToDoubleFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyToDoubleFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyToDoubleFunction<Integer, ?> toDoubleFunction = input -> input + 0.3;
        double result = toDoubleFunction.applyAsDouble(10);
        assertEquals(10.3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyToDoubleFunction<Integer, ?> toDoubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> toDoubleFunction.applyAsDouble(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyToDoubleFunction<Integer, ?> toDoubleFunction = input -> input + 0.3;
        double result = sneaky(toDoubleFunction).applyAsDouble(10);
        assertEquals(10.3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyToDoubleFunction<Integer, ?> toDoubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(toDoubleFunction).applyAsDouble(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
