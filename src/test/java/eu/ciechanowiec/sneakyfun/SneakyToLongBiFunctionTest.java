package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyToLongBiFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyToLongBiFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyToLongBiFunction<Integer, Integer, ?> toLongBiFunction = (inputOne, inputTwo) -> inputOne / inputTwo;
        long result = toLongBiFunction.applyAsLong(10, 5);
        assertEquals(2, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyToLongBiFunction<Integer, Integer, ?> toLongBiFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> toLongBiFunction.applyAsLong(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyToLongBiFunction<Integer, Integer, ?> toLongBiFunction = (inputOne, inputTwo) -> inputOne / inputTwo;
        long result = sneaky(toLongBiFunction).applyAsLong(10, 5);
        assertEquals(2, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyToLongBiFunction<Integer, Integer, ?> toLongBiFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(toLongBiFunction).applyAsLong(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
