package es.iesjuanbosco;

import com.sun.source.util.SourcePositions;
import es.iesjuanbosco.dao.interfaces.DepartamentoDao;
import es.iesjuanbosco.dao.implementation.DepartamentoDaoImpl;
import es.iesjuanbosco.exception.ValidationException;
import es.iesjuanbosco.model.Departamento;
import es.iesjuanbosco.service.DepartamentoService;
import org.mariadb.jdbc.plugin.tls.main.DefaultTlsSocketPlugin;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

            System.out.println("Hello and welcome!");
            DepartamentoDao departamentoDao = new DepartamentoDaoImpl();
            DepartamentoService departamentoService = new DepartamentoService(departamentoDao);
            insertar(departamentoService, 1, "Informática", "Madrid");
            insertar(departamentoService, 2, "Ventas", "Barcelona");
            insertar(departamentoService, 3, "Contabilidad", "Sevilla");
            insertar(departamentoService, 4, "Logística", "Valencia");
            insertar(departamentoService, 0, "Finanzas", "Albacete");
            insertar(departamentoService, 5, "", "Zaragoza");
            listarDepartamentos(departamentoService);
            actualizarLocalidadDep(departamentoService, "Ventas", "Toledo");
            listarDepartamentos(departamentoService);
            actualizarLocalidadDep(departamentoService, "DBS", "Madrid");
    }

    private static void insertar(DepartamentoService s, int id, String nombre, String loc) {
        try {
            s.insertarDepartamento(id, nombre, loc);
            System.out.println("Departamento " + id + " insertado correctamente");
        } catch (ValidationException e) {
            System.out.println("Dato no válido: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    private static void listarDepartamentos(DepartamentoService s) {
        try {
            List<Departamento> departamentos = s.listarDepartamentos();
            System.out.println("Listado de departamentos:");
            System.out.println("------------------------");
            for (Departamento d : departamentos) {
                System.out.println("Departamento: " + d.getIdDep() + " - " + d.getNombreDep() + " - " + d.getLocalidad());
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    private static void actualizarLocalidadDep(DepartamentoService s, String nombreDep, String nuevaLocalidad){
        try{
            int filasAct=s.actualizarLocalidadDep(nombreDep,nuevaLocalidad);
            System.out.println("Filas actualizadas en la tabla DEPARTAMENTOS: " + filasAct);
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (ValidationException e) {
            System.out.println("Dato no válido: " + e.getMessage());
        }
    }
}