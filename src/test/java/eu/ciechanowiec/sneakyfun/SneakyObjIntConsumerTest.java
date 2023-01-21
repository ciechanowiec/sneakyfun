package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyObjIntConsumer.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyObjIntConsumerTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyObjIntConsumer<String, ?> objIntConsumer = (inputOne, inputTwo) -> {
            String repeatedString = inputOne.repeat(inputTwo);
            stringBuilder.append(repeatedString);
        };
        objIntConsumer.accept("AB", 2);
        assertEquals("ABAB", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyObjIntConsumer<String, ?> objIntConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> objIntConsumer.accept("AB", 2));
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyObjIntConsumer<String, ?> objIntConsumer = (inputOne, inputTwo) -> {
            String repeatedString = inputOne.repeat(inputTwo);
            stringBuilder.append(repeatedString);
        };
        sneaky(objIntConsumer).accept("AB", 2);
        assertEquals("ABAB", stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyObjIntConsumer<String, ?> objIntConsumer = (inputOne, inputTwo) -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(objIntConsumer).accept("AB", 2));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
