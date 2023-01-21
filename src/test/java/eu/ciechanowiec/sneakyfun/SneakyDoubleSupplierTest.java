package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleSupplier.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleSupplierTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyDoubleSupplier<?> doubleSupplier = () -> 15;
        double result = doubleSupplier.getAsDouble();
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleSupplier<?> doubleSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, doubleSupplier::getAsDouble);
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyDoubleSupplier<?> doubleSupplier = () -> 15;
        double result = sneaky(doubleSupplier).getAsDouble();
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleSupplier<?> doubleSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleSupplier).getAsDouble());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
