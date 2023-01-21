package eu.ciechanowiec.sneakyfun;

import lombok.experimental.UtilityClass;

@UtilityClass
class Thrower {

    @SuppressWarnings("unchecked")
    static<X extends Exception, T> T sneakilyThrow(Exception exceptionToThrow) throws X {
        throw (X) exceptionToThrow;
    }
}
