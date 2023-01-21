package eu.ciechanowiec.sneakyfun;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyLongConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyLongConsumerTest {

    @SuppressWarnings("PackageVisibleInnerClass")
    static class LongHolder {

        @Setter
        @Getter
        private long value;

        LongHolder(long value) {
            this.value = value;
        }
    }

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        LongHolder longHolder = new LongHolder(NumberUtils.LONG_ZERO);
        SneakyLongConsumer<?> longConsumer = longHolder::setValue;
        longConsumer.accept(10);
        assertEquals(10, longHolder.getValue());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyLongConsumer<?> longConsumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> longConsumer.accept(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        LongHolder longHolder = new LongHolder(NumberUtils.LONG_ZERO);
        SneakyLongConsumer<?> longConsumer = longHolder::setValue;
        sneaky(longConsumer).accept(10);
        assertEquals(10, longHolder.getValue());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyLongConsumer<?> longConsumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(longConsumer).accept(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
