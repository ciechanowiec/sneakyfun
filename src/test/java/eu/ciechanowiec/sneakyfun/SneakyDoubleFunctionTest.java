package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoubleFunction<Integer, ?> doubleFunction = input -> (int) input;
        int result = doubleFunction.apply(3.5);
        assertEquals(3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleFunction<Integer, ?> doubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doubleFunction.apply(3.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoubleFunction<Integer, ?> doubleFunction = input -> (int) input;
        int result = sneaky(doubleFunction).apply(3.5);
        assertEquals(3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleFunction<Integer, ?> doubleFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleFunction).apply(3.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
