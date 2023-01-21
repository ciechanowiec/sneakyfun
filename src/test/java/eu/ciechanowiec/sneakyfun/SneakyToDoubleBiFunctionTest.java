package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyToDoubleBiFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyToDoubleBiFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyToDoubleBiFunction<Integer, Integer, ?> toDoubleBiFunction = (inputOne, inputTwo) -> (double) inputOne / inputTwo;
        double result = toDoubleBiFunction.applyAsDouble(7, 2);
        assertEquals(3.5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyToDoubleBiFunction<Integer, Integer, ?> toDoubleBiFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> toDoubleBiFunction.applyAsDouble(7, 2));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyToDoubleBiFunction<Integer, Integer, ?> toDoubleBiFunction = (inputOne, inputTwo) -> (double) inputOne / inputTwo;
        double result = sneaky(toDoubleBiFunction).applyAsDouble(7, 2);
        assertEquals(3.5, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyToDoubleBiFunction<Integer, Integer, ?> toDoubleBiFunction = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(toDoubleBiFunction).applyAsDouble(7, 2));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
