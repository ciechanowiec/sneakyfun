package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntFunction<Integer, ?> intFunction = input -> input + input;
        int result = intFunction.apply(10);
        assertEquals(20, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntFunction<Integer, ?> intFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intFunction.apply(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntFunction<Integer, ?> intFunction = input -> input + input;
        int result = sneaky(intFunction).apply(10);
        assertEquals(20, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntFunction<Integer, ?> intFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intFunction).apply(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
