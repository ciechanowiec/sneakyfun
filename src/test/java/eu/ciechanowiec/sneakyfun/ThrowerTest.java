package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ThrowerTest {

    @Test
    void sneakilyThrow() {
        SneakyException exceptionToThrow = new SneakyException();
        assertThrows(SneakyException.class, () -> Thrower.sneakilyThrow(exceptionToThrow));
    }
}
