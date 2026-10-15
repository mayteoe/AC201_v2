package es.iesjuanbosco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Departamento {
    private int idDep;          // columna id_dep
    private String nombreDep;   // columna nombre_dep
    private String localidad;
}
