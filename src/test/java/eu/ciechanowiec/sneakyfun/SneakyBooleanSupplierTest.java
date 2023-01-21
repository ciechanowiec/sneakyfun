package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyBooleanSupplier.sneaky;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SneakyBooleanSupplierTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyBooleanSupplier<?> booleanSupplier = () -> 4 % 2 == 0;
        boolean result = booleanSupplier.getAsBoolean();
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyBooleanSupplier<?> booleanSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> booleanSupplier.getAsBoolean());
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyBooleanSupplier<?> booleanSupplier = () -> 4 % 2 == 0;
        boolean result = sneaky(booleanSupplier).getAsBoolean();
        assertTrue(result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyBooleanSupplier<?> booleanSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(booleanSupplier).getAsBoolean());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
