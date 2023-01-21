package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntBinaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntBinaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntBinaryOperator<?> intBinaryOperator = Integer::sum;
        int result = intBinaryOperator.applyAsInt(10, 5);
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntBinaryOperator<?> intBinaryOperator = (leftOperand, rightOperand) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intBinaryOperator.applyAsInt(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntBinaryOperator<?> intBinaryOperator = Integer::sum;
        int result = sneaky(intBinaryOperator).applyAsInt(10, 5);
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntBinaryOperator<?> intBinaryOperator = (leftOperand, rightOperand) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intBinaryOperator).applyAsInt(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
