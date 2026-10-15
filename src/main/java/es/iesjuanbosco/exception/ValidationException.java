package es.iesjuanbosco.exception;
/**
 * Se lanza cuando un dato NO cumple las reglas de negocio
 * (por ejemplo: un nombre vacío, un DNI mal escrito o un departamento inexistente).
 *
 * Es una excepción PROPIA, distinta de SQLException, para poder distinguir:
 *   - ValidacionException -> el error es del dato que ha dado el usuario
 *   - SQLException        -> el error viene de la base de datos o de la conexión
 */

public class ValidationException extends Exception{

    public ValidationException(String message) {
        super(message);
    }
}
