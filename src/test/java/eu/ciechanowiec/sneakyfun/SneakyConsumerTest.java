package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyConsumerTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyConsumer<String, ?> consumer = stringBuilder::append;
        consumer.accept(Variables.UNO);
        assertEquals(Variables.UNO, stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyConsumer<String, ?> consumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> consumer.accept(Variables.UNO));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyConsumer<String, ?> consumer = stringBuilder::append;
        sneaky(consumer).accept(Variables.UNO);
        assertEquals(Variables.UNO, stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyConsumer<String, ?> consumer = input -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(consumer).accept(Variables.UNO));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
