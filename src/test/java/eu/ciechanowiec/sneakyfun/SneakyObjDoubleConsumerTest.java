package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyObjDoubleConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyObjDoubleConsumerTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyObjDoubleConsumer<String, ?> objDoubleConsumer = (inputOne, inputTwo) -> {
            String repeatedString = inputOne.repeat((int) inputTwo);
            stringBuilder.append(repeatedString);
        };
        objDoubleConsumer.accept("AB", 2);
        assertEquals("ABAB", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyObjDoubleConsumer<String, ?> objDoubleConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> objDoubleConsumer.accept("AB", 2));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyObjDoubleConsumer<String, ?> objDoubleConsumer = (inputOne, inputTwo) -> {
            String repeatedString = inputOne.repeat((int) inputTwo);
            stringBuilder.append(repeatedString);
        };
        sneaky(objDoubleConsumer).accept("AB", 2);
        assertEquals("ABAB", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyObjDoubleConsumer<String, ?> objDoubleConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(objDoubleConsumer).accept("AB", 2));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
