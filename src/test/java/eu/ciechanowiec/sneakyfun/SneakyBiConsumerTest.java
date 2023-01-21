package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyBiConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyBiConsumerTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyBiConsumer<String, String, ?> biConsumer = (inputOne, inputTwo) -> {
            stringBuilder.append(inputOne)
                    .append(inputTwo);
        };
        biConsumer.accept("Uno", "Duo");
        assertEquals("UnoDuo", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyBiConsumer<String, String, ?> biConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> biConsumer.accept(Variables.UNO, Variables.DUO));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyBiConsumer<String, String, ?> biConsumer = (inputOne, inputTwo) -> {
            stringBuilder.append(inputOne)
                         .append(inputTwo);
        };
        sneaky(biConsumer).accept("Uno", "Duo");
        assertEquals("UnoDuo", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyBiConsumer<String, String, ?> biConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(biConsumer).accept(Variables.UNO, Variables.DUO));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
