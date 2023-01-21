package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyToIntFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyToIntFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyToIntFunction<Integer, ?> toIntFunction = input -> input + 2;
        int result = toIntFunction.applyAsInt(10);
        assertEquals(12, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyToIntFunction<Integer, ?> toIntFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> toIntFunction.applyAsInt(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyToIntFunction<Integer, ?> toIntFunction = input -> input + 2;
        int result = sneaky(toIntFunction).applyAsInt(10);
        assertEquals(12, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyToIntFunction<Integer, ?> toIntFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(toIntFunction).applyAsInt(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
