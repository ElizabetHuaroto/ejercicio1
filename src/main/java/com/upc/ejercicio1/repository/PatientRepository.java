package com.upc.ejercicio1.repository;

import com.upc.ejercicio1.entitys.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE p.name like %:prefijo%")
    public List<Patient> obtenerReportePorNombre(@Param("prefijo") String prefijo);
    public List<Patient> findByNameStartingWith(String prefix);
    public List<Patient> findByEmailIs(String email);
}
