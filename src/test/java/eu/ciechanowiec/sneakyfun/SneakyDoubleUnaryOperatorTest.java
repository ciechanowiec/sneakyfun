package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleUnaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleUnaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoubleUnaryOperator<?> doubleUnaryOperator = operand -> operand * operand;
        double result = doubleUnaryOperator.applyAsDouble(3.5);
        assertEquals(12.25, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleUnaryOperator<?> doubleUnaryOperator = operand -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doubleUnaryOperator.applyAsDouble(3.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoubleUnaryOperator<?> doubleUnaryOperator = operand -> operand * operand;
        double result = sneaky(doubleUnaryOperator).applyAsDouble(3.5);
        assertEquals(12.25, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleUnaryOperator<?> doubleUnaryOperator = operand -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleUnaryOperator).applyAsDouble(3.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
