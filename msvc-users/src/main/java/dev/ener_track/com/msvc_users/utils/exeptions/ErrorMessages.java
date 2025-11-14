package dev.ener_track.com.msvc_users.utils.exeptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ErrorMessages {

    public static String IdNotFound(String entity) {
        final String message = "There are no records in the entity %s with  id";
        return String.format(message, entity);
    }

    public static String alreadyExists(Object value) {
        return String.format("%s already exists in the database", value);
    }

    public static String emailNotFound(Object value) {
        return String.format("%s not exists in the database", value);
    }

    public static String userNotFound(Object value) {
        return String.format("%s not exists in the database", value);
    }
}
