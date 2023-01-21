package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyUnaryOperator.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyUnaryOperatorTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyUnaryOperator<Integer, ?> unaryOperator = input -> input + input;
        int result = unaryOperator.apply(10);
        assertEquals(20, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyUnaryOperator<Integer, ?> unaryOperator = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> unaryOperator.apply(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyUnaryOperator<Integer, ?> unaryOperator = input -> input + input;
        int result = sneaky(unaryOperator).apply(10);
        assertEquals(20, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyUnaryOperator<Integer, ?> unaryOperator = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(unaryOperator).apply(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
