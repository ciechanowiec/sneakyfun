package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyToIntBiFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyToIntBiFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyToIntBiFunction<Integer, Integer, ?> toIntBiFunction = (inputOne, inputTwo) -> inputOne / inputTwo;
        int result = toIntBiFunction.applyAsInt(10, 5);
        assertEquals(2, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyToIntBiFunction<Integer, Integer, ?> toIntBiFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> toIntBiFunction.applyAsInt(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyToIntBiFunction<Integer, Integer, ?> toIntBiFunction = (inputOne, inputTwo) -> inputOne / inputTwo;
        int result = sneaky(toIntBiFunction).applyAsInt(10, 5);
        assertEquals(2, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyToIntBiFunction<Integer, Integer, ?> toIntBiFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(toIntBiFunction).applyAsInt(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
