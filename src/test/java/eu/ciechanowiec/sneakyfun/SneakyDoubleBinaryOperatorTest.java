package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleBinaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleBinaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoubleBinaryOperator<?> doubleBinaryOperator = Double::sum;
        double result = doubleBinaryOperator.applyAsDouble(3.5, 1.5);
        assertEquals(5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleBinaryOperator<?> doubleBinaryOperator = (leftOperand, rightOperand) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doubleBinaryOperator.applyAsDouble(3.5, 1.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoubleBinaryOperator<?> doubleBinaryOperator = Double::sum;
        double result = sneaky(doubleBinaryOperator).applyAsDouble(3.5, 1.5);
        assertEquals(5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleBinaryOperator<?> doubleBinaryOperator = (leftOperand, rightOperand) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleBinaryOperator).applyAsDouble(3.5, 1.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
