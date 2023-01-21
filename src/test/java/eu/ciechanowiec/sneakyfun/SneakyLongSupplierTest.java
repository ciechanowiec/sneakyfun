package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongSupplier.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongSupplierTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyLongSupplier<?> longSupplier = () -> Long.MAX_VALUE;
        long result = longSupplier.getAsLong();
        assertEquals(Long.MAX_VALUE, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongSupplier<?> longSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, longSupplier::getAsLong);
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyLongSupplier<?> longSupplier = () -> Long.MAX_VALUE;
        long result = sneaky(longSupplier).getAsLong();
        assertEquals(Long.MAX_VALUE, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongSupplier<?> longSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longSupplier).getAsLong());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
