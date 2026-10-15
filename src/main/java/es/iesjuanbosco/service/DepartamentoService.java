package es.iesjuanbosco.service;

import es.iesjuanbosco.dao.interfaces.DepartamentoDao;
import es.iesjuanbosco.exception.ValidationException;
import es.iesjuanbosco.model.Departamento;
import java.sql.SQLException;
import java.util.List;

public class DepartamentoService {

    private final DepartamentoDao departamentoDao;
    private static final int MAX_NOMBRE_LENGTH = 50; // Longitud máxima permitida para el nombre del departamento
    private static final int MAX_LOCALIDAD_LENGTH = 50; // Longitud máxima permitida para la localidad del departamento
    private static final int MIN_ID = 1; // Valor mínimo permitido para el id del departamento


    public DepartamentoService(DepartamentoDao departamentoDao) {
        this.departamentoDao = departamentoDao;
    }



    public void insertarDepartamento(int idDep, String nombreDep, String localidad) throws ValidationException, SQLException {

        if (idDep < MIN_ID) {
            throw new ValidationException("El id del departamento debe ser mayor o igual a " + MIN_ID);
        }
        campoValido("NombreDep", nombreDep, MAX_NOMBRE_LENGTH);
        campoValido("Localidad", localidad, MAX_LOCALIDAD_LENGTH);

        if (departamentoDao.existeIdDep(idDep)) {
            throw new ValidationException("El id "+ idDep+" del departamento ya existe");
        }
        //Todos los campos son válidos y el id no existe, se puede insertar el departamento
            Departamento departamento = new Departamento(idDep, nombreDep, localidad);
            departamentoDao.insertarDepartamento(departamento);



    }
    public List<Departamento> listarDepartamentos() throws SQLException{
        return departamentoDao.listarDepartamentos();
    }

    public int actualizarLocalidadDep(String nombreDep,String nuevaLocalidad) throws SQLException, ValidationException {
        if (nuevaLocalidad == null || nuevaLocalidad.isEmpty()) {
            throw new ValidationException("La nueva localidad no puede estar vacía");
        }
        campoValido("Localidad", nuevaLocalidad, MAX_LOCALIDAD_LENGTH);
        if (nombreDep == null || nombreDep.isEmpty()) {
            throw new ValidationException("El nombre del departamento no puede estar vacío");
        }
        campoValido("NombreDep", nombreDep, MAX_NOMBRE_LENGTH);
        campoValido("Localidad", nuevaLocalidad, MAX_LOCALIDAD_LENGTH);

        int filasActualizadas = departamentoDao.actualizarLocalidad(nombreDep, nuevaLocalidad);
        if (filasActualizadas == 0) {
            throw new ValidationException("No se encontró ningún departamento con el nombre " + nombreDep);
        }
        return filasActualizadas;

       }



    private void campoValido(String campo, String valor, int maxLength) throws ValidationException{
        if (valor == null || valor.isEmpty()) {
            throw new ValidationException("El campo " + campo + " no puede estar vacío");
        }
        if (valor.length() > maxLength) {
            throw new ValidationException("El campo " + campo + " no puede superar los " + maxLength + " caracteres");
        }

    }
}