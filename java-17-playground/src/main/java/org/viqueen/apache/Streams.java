package org.viqueen.apache;

import java.util.function.Function;
import java.util.logging.Logger;

public class Streams {

    private static final Logger LOG = Logger.getLogger(Streams.class.getCanonicalName());

    public static <TYPE, RETURN> Function<TYPE, RETURN> toValueOrNull(CheckedExceptionFunction<TYPE, RETURN> function) {
        return item -> {
            try {
                return function.apply(item);
            } catch (Exception exception) {
                LOG.warning(exception.getMessage());
                return null;
            }
        };
    }

    @FunctionalInterface
    public interface CheckedExceptionFunction<TYPE, RETURN> {
        RETURN apply(TYPE item) throws Exception;
    }

}
