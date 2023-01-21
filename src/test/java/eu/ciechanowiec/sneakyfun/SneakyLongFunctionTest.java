package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongFunction<Long, ?> longFunction = input -> input / 10;
        long result = longFunction.apply(100);
        assertEquals(10, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongFunction<Long, ?> longFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longFunction.apply(100));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongFunction<Long, ?> longFunction = input -> input / 10;
        long result = sneaky(longFunction).apply(100);
        assertEquals(10, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongFunction<Long, ?> longFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longFunction).apply(100));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
