package eu.ciechanowiec.sneakyfun;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyDoubleConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyDoubleConsumerTest {

    @SuppressWarnings("PackageVisibleInnerClass")
    static class DoubleHolder {

        @Setter
        @Getter
        private double value;

        DoubleHolder(double value) {
            this.value = value;
        }
    }

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        DoubleHolder doubleHolder = new DoubleHolder(NumberUtils.DOUBLE_ZERO);
        SneakyDoubleConsumer<?> doubleConsumer = doubleHolder::setValue;
        doubleConsumer.accept(3.5);
        assertEquals(3.5, doubleHolder.getValue());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyDoubleConsumer<?> doubleConsumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> doubleConsumer.accept(3.5));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        DoubleHolder doubleHolder = new DoubleHolder(NumberUtils.DOUBLE_ZERO);
        SneakyDoubleConsumer<?> doubleConsumer = doubleHolder::setValue;
        sneaky(doubleConsumer).accept(3.5);
        assertEquals(3.5, doubleHolder.getValue());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyDoubleConsumer<?> doubleConsumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(doubleConsumer).accept(3.5));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
