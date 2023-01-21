package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyBinaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyBinaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyBinaryOperator<Integer, ?> binaryOperator = Integer::sum;
        int result = binaryOperator.apply(10, 5);
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyBinaryOperator<Integer, ?> binaryOperator = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> binaryOperator.apply(10, 5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyBinaryOperator<Integer, ?> binaryOperator = Integer::sum;
        int result = sneaky(binaryOperator).apply(10, 5);
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyBinaryOperator<Integer, ?> binaryOperator = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(binaryOperator).apply(10, 5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
