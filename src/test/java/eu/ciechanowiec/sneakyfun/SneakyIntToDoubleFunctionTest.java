package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntToDoubleFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntToDoubleFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntToDoubleFunction<?> intToDoubleFunction = input -> input + 0.5;
        double result = intToDoubleFunction.applyAsDouble(10);
        assertEquals(10.5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntToDoubleFunction<?> intToDoubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intToDoubleFunction.applyAsDouble(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntToDoubleFunction<?> intToDoubleFunction = input -> input + 0.5;
        double result = sneaky(intToDoubleFunction).applyAsDouble(10);
        assertEquals(10.5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntToDoubleFunction<?> intToDoubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intToDoubleFunction).applyAsDouble(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
