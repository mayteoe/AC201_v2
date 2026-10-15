package es.iesjuanbosco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private int idEmp;
    private String nombre;
    private String apellido;
    private String oficio;
    private String dni;
    private LocalDate fechaAlta;
    private double salario;
    private Double comision;     // null = sin comisión
    private int idDep;
}
