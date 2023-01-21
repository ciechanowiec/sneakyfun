package eu.ciechanowiec.sneakyfun;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakySupplier.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakySupplierTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        SneakySupplier<Integer, ?> supplier = () -> NumberUtils.INTEGER_ONE;
        int result = supplier.get();
        assertEquals(NumberUtils.INTEGER_ONE, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakySupplier<Integer, ?> supplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, supplier::get);
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        SneakySupplier<Integer, ?> supplier = () -> NumberUtils.INTEGER_ONE;
        int result = sneaky(supplier).get();
        assertEquals(NumberUtils.INTEGER_ONE, result);
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakySupplier<Integer, ?> supplier = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(supplier).get());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
