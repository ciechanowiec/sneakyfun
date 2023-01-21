package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntUnaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntUnaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntUnaryOperator<?> intUnaryOperator = operand -> operand * operand;
        int result = intUnaryOperator.applyAsInt(10);
        assertEquals(100, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntUnaryOperator<?> intUnaryOperator = operand -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intUnaryOperator.applyAsInt(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntUnaryOperator<?> intUnaryOperator = operand -> operand * operand;
        int result = sneaky(intUnaryOperator).applyAsInt(10);
        assertEquals(100, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntUnaryOperator<?> intUnaryOperator = operand -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intUnaryOperator).applyAsInt(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
