package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyFunction<Integer, Integer, ?> function = input -> input + input;
        int result = function.apply(10);
        assertEquals(20, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyFunction<Integer, Integer, ?> function = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> function.apply(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyFunction<Integer, Integer, ?> function = input -> input + input;
        int result = sneaky(function).apply(10);
        assertEquals(20, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyFunction<Integer, Integer, ?> function = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(function).apply(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
