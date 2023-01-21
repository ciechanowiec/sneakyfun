package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyObjLongConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyObjLongConsumerTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyObjLongConsumer<String, ?> objLongConsumer = (inputOne, inputTwo) -> {
            String repeatedString = inputOne.repeat((int) inputTwo);
            stringBuilder.append(repeatedString);
        };
        objLongConsumer.accept("AB", 2);
        assertEquals("ABAB", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyObjLongConsumer<String, ?> objLongConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> objLongConsumer.accept("AB", 2));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyObjLongConsumer<String, ?> objLongConsumer = (inputOne, inputTwo) -> {
            String repeatedString = inputOne.repeat((int) inputTwo);
            stringBuilder.append(repeatedString);
        };
        sneaky(objLongConsumer).accept("AB", 2);
        assertEquals("ABAB", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyObjLongConsumer<String, ?> objLongConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(objLongConsumer).accept("AB", 2));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
