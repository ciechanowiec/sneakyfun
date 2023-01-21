package eu.ciechanowiec.sneakyfun;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static eu.ciechanowiec.sneakyfun.SneakyFunction.sneaky;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamsTest {

    @Test
    void mustConvertURIsWithinStream() {
        List<String> rawURIs = List.of("google.com", "ciechanowiec.eu");
        List<URI> pureURIs = rawURIs.stream()
                .map(sneaky(URI::new))
                .collect(Collectors.toList());
        List<String> unwrappedURIs = pureURIs.stream()
                                             .map(URI::toString)
                                             .collect(Collectors.toList());
        assertAll(
                () -> assertEquals("google.com", unwrappedURIs.get(0)),
                () -> assertEquals("ciechanowiec.eu", unwrappedURIs.get(1))
        );
    }
}
