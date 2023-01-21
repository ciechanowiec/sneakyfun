package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyToLongFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyToLongFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyToLongFunction<Integer, ?> toLongFunction = input -> input / 2;
        long result = toLongFunction.applyAsLong(10);
        assertEquals(5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyToLongFunction<Integer, ?> toLongFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> toLongFunction.applyAsLong(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyToLongFunction<Integer, ?> toLongFunction = input -> input / 2;
        long result = sneaky(toLongFunction).applyAsLong(10);
        assertEquals(5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyToLongFunction<Integer, ?> toLongFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(toLongFunction).applyAsLong(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
