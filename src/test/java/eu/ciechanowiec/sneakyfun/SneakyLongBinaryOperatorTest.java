package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongBinaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongBinaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongBinaryOperator<?> longBinaryOperator = (leftOperand, rightOperand) -> leftOperand / rightOperand;
        long result = longBinaryOperator.applyAsLong(10, 5);
        assertEquals(2, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongBinaryOperator<?> longBinaryOperator = (leftOperand, rightOperand) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longBinaryOperator.applyAsLong(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongBinaryOperator<?> longBinaryOperator = (leftOperand, rightOperand) -> leftOperand / rightOperand;
        long result = sneaky(longBinaryOperator).applyAsLong(10, 5);
        assertEquals(2, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongBinaryOperator<?> longBinaryOperator = (leftOperand, rightOperand) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longBinaryOperator).applyAsLong(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
