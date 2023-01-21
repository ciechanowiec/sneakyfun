package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongUnaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongUnaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongUnaryOperator<?> longUnaryOperator = operand -> operand * operand;
        long result = longUnaryOperator.applyAsLong(6);
        assertEquals(36, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongUnaryOperator<?> longUnaryOperator = operand -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longUnaryOperator.applyAsLong(6));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongUnaryOperator<?> longUnaryOperator = operand -> operand * operand;
        long result = sneaky(longUnaryOperator).applyAsLong(6);
        assertEquals(36, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongUnaryOperator<?> longUnaryOperator = operand -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longUnaryOperator).applyAsLong(6));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
