package co.id.web.util;

import co.id.web.exception.MyConflictException;
import co.id.web.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

public final class RestUtil {
    public static void propagateStatusCodeOnCreate(final ResponseEntity<?> createResponse, final String message) {
        if (createResponse.getStatusCode().value() == 409) {
            throw new MyConflictException(message);
        }

        if (createResponse.getStatusCode().value() != 201) {
            throw new IllegalStateException(message);
        }
    }

    public static void propagateStatusCodeOnException(final HttpStatusCodeException ex, final String message) {
        if (ex.getStatusCode().value() == 409) {
            throw new ValidationException(ex.getStatusText());
        }

        throw new IllegalStateException(message);
    }
}