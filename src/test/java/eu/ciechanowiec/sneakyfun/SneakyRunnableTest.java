package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static eu.ciechanowiec.sneakyfun.SneakyRunnable.sneaky;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SneakyRunnableTest {

    @Test
    void mustExecuteBasicMethodNormally() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyRunnable<?> runnable = () -> stringBuilder.append(Variables.UNO);
        runnable.run();
        assertEquals(Variables.UNO, stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodNormally() {
        SneakyRunnable<?> runnable = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, runnable::run);
    }

    @Test
    void mustExecuteBasicMethodSneakily() {
        StringBuilder stringBuilder = new StringBuilder();
        SneakyRunnable<?> runnable = () -> stringBuilder.append(Variables.UNO);
        sneaky(runnable).run();
        assertEquals(Variables.UNO, stringBuilder.toString());
    }

    @Test
    void mustThrowWhenExecutingBasicMethodSneakily() {
        SneakyRunnable<?> runnable = () -> {
            throw new SneakyException();
        };
        assertThrows(SneakyException.class, () -> sneaky(runnable).run());
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void mustThrowNPE() {
        assertThrows(NullPointerException.class, () -> sneaky(null));
    }
}
