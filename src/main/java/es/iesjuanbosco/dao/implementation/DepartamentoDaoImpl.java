package es.iesjuanbosco.dao.implementation;

import es.iesjuanbosco.conexion.ConexionDB;
import es.iesjuanbosco.dao.interfaces.DepartamentoDao;
import es.iesjuanbosco.model.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoImpl implements DepartamentoDao {

    private static final String SQL_FIND_DEP="SELECT * FROM DEPARTAMENTOS WHERE id_dep=?";
    private static final String SQL_INS_DEP="INSERT INTO DEPARTAMENTOS (id_dep,nombre_dep,localidad) VALUES (?,?,?)";
    private static final String SQL_LIST_DEP="SELECT * FROM DEPARTAMENTOS";
    private static final String SQL_UPDATE_LOC="UPDATE DEPARTAMENTOS SET localidad=? WHERE nombre_dep=?";
    @Override
    public boolean existeIdDep(int idDep) throws SQLException {
        try (Connection con = ConexionDB.getConnection();PreparedStatement pst=con.prepareStatement(SQL_FIND_DEP)){

            pst.setInt(1, idDep);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }
 @Override
    public void insertarDepartamento(Departamento departamento) throws SQLException {
        try (Connection con=ConexionDB.getConnection(); PreparedStatement ps=con.prepareStatement(SQL_INS_DEP)){
            ps.setInt(1,departamento.getIdDep());
            ps.setString(2,departamento.getNombreDep());
            ps.setString(3,departamento.getLocalidad());
            ps.executeUpdate();
        }
    }

    public List<Departamento> listarDepartamentos() throws SQLException {
        String SQL_LIST_DEP = "SELECT * FROM DEPARTAMENTOS";    ;
        List<Departamento> departamentos = new ArrayList<>();
        try (Connection con = ConexionDB.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_DEP); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idDep = rs.getInt("id_dep");
                String nombreDep = rs.getString("nombre_dep");
                String localidad = rs.getString("localidad");
                Departamento departamento = new Departamento(idDep, nombreDep, localidad);
                departamentos.add(departamento);
            }
            return departamentos;
        }

    }
    @Override
    public int actualizarLocalidad(String nombreDep, String nuevaLocalidad) throws SQLException {
        try (Connection con = ConexionDB.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_LOC)){
            ps.setString(1,nuevaLocalidad);
            ps.setString(2, nombreDep);
            return ps.executeUpdate();
        }
    }
}
