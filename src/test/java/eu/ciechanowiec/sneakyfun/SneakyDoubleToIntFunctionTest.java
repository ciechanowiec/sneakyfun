package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleToIntFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleToIntFunctionTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoubleToIntFunction<?> doubleToIntFunction = input -> (int) input;
        int result = doubleToIntFunction.applyAsInt(3.5);
        assertEquals(3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleToIntFunction<?> doubleToIntFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doubleToIntFunction.applyAsInt(3.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoubleToIntFunction<?> doubleToIntFunction = input -> (int) input;
        int result = sneaky(doubleToIntFunction).applyAsInt(3.5);
        assertEquals(3, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleToIntFunction<?> doubleToIntFunction = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleToIntFunction).applyAsInt(3.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
