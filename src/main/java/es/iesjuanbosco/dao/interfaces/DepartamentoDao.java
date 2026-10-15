package es.iesjuanbosco.dao.interfaces;

import es.iesjuanbosco.model.Departamento;


import java.sql.SQLException;
import java.util.List;

public interface DepartamentoDao {
    boolean existeIdDep(int idDep) throws SQLException;
    void insertarDepartamento(Departamento departamento) throws SQLException;
    List<Departamento> listarDepartamentos() throws SQLException;
    int  actualizarLocalidad(String nombreDep, String nuevaLocalidad) throws SQLException;


}
