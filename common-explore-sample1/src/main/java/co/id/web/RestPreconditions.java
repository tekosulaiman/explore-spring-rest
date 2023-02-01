package co.id.web;

import co.id.web.exception.MyBadRequestException;
import co.id.web.exception.MyConflictException;
import co.id.web.exception.MyResourceNotFoundException;

public final class RestPreconditions {
    private RestPreconditions() {
        throw new AssertionError();
    }

    public static <T> T checkNotNull(final T reference) {
        return checkNotNull(reference, null);
    }

    public static <T> T checkNotNull(final T reference, final String message) {
        if (reference == null) {
            throw new MyResourceNotFoundException(message);
        }
        return reference;
    }

    public static <T> T checkRequestElementNotNull(final T reference) {
        return checkRequestElementNotNull(reference, null);
    }

    public static <T> T checkRequestElementNotNull(final T reference, final String message) {
        if (reference == null) {
            throw new MyBadRequestException(message);
        }
        return reference;
    }

    public static void checkRequestState(final boolean expression) {
        checkRequestState(expression, null);
    }

    public static void checkRequestState(final boolean expression, final String message) {
        if (!expression) {
            throw new MyConflictException(message);
        }
    }
}