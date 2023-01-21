package eu.ciechanowiec.sneakyfun;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyIntConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyIntConsumerTest {

    @SuppressWarnings("PackageVisibleInnerClass")
    static class IntHolder {

        @Setter
        @Getter
        private int value;

        IntHolder(int value) {
            this.value = value;
        }
    }

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        IntHolder intHolder = new IntHolder(NumberUtils.INTEGER_ZERO);
        SneakyIntConsumer<?> intConsumer = intHolder::setValue;
        intConsumer.accept(10);
        assertEquals(10, intHolder.getValue());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyIntConsumer<?> intConsumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> intConsumer.accept(10));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        IntHolder intHolder = new IntHolder(NumberUtils.INTEGER_ZERO);
        SneakyIntConsumer<?> intConsumer = intHolder::setValue;
        sneaky(intConsumer).accept(10);
        assertEquals(10, intHolder.getValue());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyIntConsumer<?> intConsumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(intConsumer).accept(10));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
