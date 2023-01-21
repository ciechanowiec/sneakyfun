package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntSupplier.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntSupplierTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakyIntSupplier<?> intSupplier = () -> 15;
        int result = intSupplier.getAsInt();
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntSupplier<?> intSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, intSupplier::getAsInt);
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakyIntSupplier<?> intSupplier = () -> 15;
        int result = sneaky(intSupplier).getAsInt();
        assertEquals(15, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntSupplier<?> intSupplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intSupplier).getAsInt());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
