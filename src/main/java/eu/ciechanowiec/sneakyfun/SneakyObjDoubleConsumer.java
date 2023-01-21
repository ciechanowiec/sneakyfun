package eu.ciechanowiec.sneakyfun;

import java.util.Objects;
import java.util.function.ObjDoubleConsumer;

/**
 * Represents an operation that accepts an object-valued and a
 * {@code double}-valued argument, and returns no result. This is the
 * {@code (reference, double)} specialization of {@link SneakyBiConsumer}.
 * Unlike most other functional interfaces, {@code ObjDoubleConsumer} is
 * expected to operate via side-effects.
 *
 * <p>This is a {@link java.util.function functional interface}
 * whose functional method is {@link #accept(Object, double)}.
 *
 * @param <T> the type of the object argument to the operation
 * @param <X> the type of exception that might be thrown during execution of
 *            the functional method of this interface
 */
@FunctionalInterface
public interface SneakyObjDoubleConsumer<T, X extends Exception> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param inputOne the first input argument
     * @param inputTwo the second input argument
     * @throws X if an exception during execution of this method was thrown
     */
    void accept(T inputOne, double inputTwo) throws X;

    /**
     * Wraps the passed sneaky functional interface (<i>sneaky interface</i>) into the analogous
     * functional interface from the {@link java.util.function} package (<i>original interface</i>).
     * <p>
     * <ol>
     *   <li>
     *   Both the wrapped sneaky interface and the wrapping original interface
     *   have unary functional methods with identical method signatures and return types (if any).
     *   The only relevant difference between the unary functional methods of those interfaces is
     *   that the functional method of the sneaky interface has a {@code throws} clause with a
     *   denoted exception, while the functional method of the original interface doesn't have it.
     *   <p>
     *   For example, compare these two functional method declarations:
     *   <pre>
     *   // Functional method of the original interface `Function`:
     *      R apply(T t);
     *
     *   // Functional method of the interface `SneakyFunction`:
     *      R apply(T input) throws X;
     *   </pre>
     *   </li>
     *
     *   <li>
     *   A {@code throws} clause in the unary functional method of the wrapped sneaky interface allows
     *   to develop elegant lambda expressions via disabling enforcement of checked exceptions handling.
     *   To achieve that, simply wrap implementation of a lambda expression into a static {@code sneaky(...)}
     *   method declared in the respective sneaky interface, as shown in the examples below.
     *
     *   <li>
     *   <strong>Example - Outside Streams</strong>
     *   <ol type="A">
     *   <li><i>Classical & unsightly approach:</i>
     *   <pre>
     *   Function<String, URI> toURI = input -> {
     *     try {
     *         return new URI(input);
     *     } catch (URISyntaxException exception) {
     *         log.error("Unable to create a URI", exception);
     *         return null;
     *     }
     *   };
     *   URI uri = toURI.apply("google.com");
     *   </pre>
     *   </li>
     *   <li><i>Sneaky & elegant approach:</i>
     *   <pre>
     *   SneakyFunction<String, URI, URISyntaxException> toURI = URI::new;
     *   Function<String, URI> toURIAdapter = sneaky(toURI);
     *   URI uri = toURIAdapter.apply("google.com");
     *   </pre>
     *   </li>
     *   </ol>
     *
     *   <li>
     *   <strong>Example - In Streams</strong>
     *   <ol type="A">
     *   <li><i>Classical & unsightly approach:</i>
     *   <pre>
     *   List<String> rawURIs = List.of("google.com", "ciechanowiec.eu");
     *   List<URI> pureURIs = rawURIs.stream()
     *                          .map(rawURI -> {
     *                              try {
     *                                  return new URI(rawURI);
     *                              } catch (URISyntaxException exception) {
     *                                  log.error("Unable to create a URI", exception);
     *                                  return null;
     *                              }
     *                          })
     *                          .toList();
     *   </pre>
     *   </li>
     *   <li><i>Sneaky & elegant approach:</i>
     *   <pre>
     *   List<String> rawURIs = List.of("google.com", "ciechanowiec.eu");
     *   List<URI> pureURIs = rawURIs.stream()
     *                          .map(sneaky(URI::new))
     *                          .toList();
     *   </pre>
     *   </li>
     *
     *   </ol>
     *   </li>
     *   <li>
     *   For details see documentation: <a href="https://github.com/ciechanowiec/sneakyfun">https://github.com/ciechanowiec/sneakyfun</a>.
     *   </li>
     * </ol>
     * @param objDoubleConsumer sneaky functional interface that will be wrapped into the analogous
     *                   original functional interface from the {@link java.util.function} package
     * @return original functional interface from the {@link java.util.function} package
     *         wrapping the passed sneaky functional interface
     * @param <T> the type of the object argument to the operation
     * @param <X> the type of exception that might be thrown during execution of
     *            the functional method of this interface
     */
    @SuppressWarnings({"squid:S2221", "OverlyBroadCatchBlock"})
    static<T, X extends Exception> ObjDoubleConsumer<T> sneaky(SneakyObjDoubleConsumer<T, X> objDoubleConsumer) {
        Objects.requireNonNull(objDoubleConsumer);
        return (inputOne, inputTwo) -> {
            try {
                objDoubleConsumer.accept(inputOne, inputTwo);
            } catch (Exception exception) {
                Thrower.sneakilyThrow(exception);
            }
        };
    }
}