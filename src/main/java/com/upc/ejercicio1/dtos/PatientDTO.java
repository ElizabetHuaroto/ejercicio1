package com.upc.ejercicio1.dtos;

import com.upc.ejercicio1.entitys.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Setter
@Getter
public class PatientDTO {
    private Long id;
    private String name;
    private LocalDate appointment;
    private String email;
    private Type type;
    //Ver si puedo poner una calculación
}
