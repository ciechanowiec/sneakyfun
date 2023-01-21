package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyBiFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyBiFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyBiFunction<Integer, Integer, Integer, ?> biFunction = Integer::sum;
        int result = biFunction.apply(10, 5);
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyBiFunction<Integer, Integer, Integer, ?> biFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> biFunction.apply(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyBiFunction<Integer, Integer, Integer, ?> biFunction = Integer::sum;
        int result = sneaky(biFunction).apply(10, 5);
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyBiFunction<Integer, Integer, Integer, ?> biFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(biFunction).apply(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
