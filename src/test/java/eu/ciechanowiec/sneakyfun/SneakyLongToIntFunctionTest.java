package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongToIntFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongToIntFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongToIntFunction<?> longToIntFunction = input -> (int) input + 1;
        int result = longToIntFunction.applyAsInt(10);
        assertEquals(11, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongToIntFunction<?> longToIntFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longToIntFunction.applyAsInt(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongToIntFunction<?> longToIntFunction = input -> (int) input + 1;
        int result = sneaky(longToIntFunction).applyAsInt(10);
        assertEquals(11, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongToIntFunction<?> longToIntFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longToIntFunction).applyAsInt(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
