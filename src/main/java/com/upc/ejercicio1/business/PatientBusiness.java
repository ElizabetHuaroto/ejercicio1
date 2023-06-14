package com.upc.ejercicio1.business;

import com.upc.ejercicio1.entitys.Patient;
import com.upc.ejercicio1.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientBusiness {
    @Autowired
    private PatientRepository patientRepository;
    @Transactional
    public Patient register(Patient patient){
        return patientRepository.save(patient);
    }
    public Patient findfunction(Long code) throws Exception {
        return patientRepository.findById(code).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<Patient> list(){
        return patientRepository.findAll();
    }

    public List<Patient> getReportbyDescription(String prefix){

        return patientRepository.findByNameStartingWith(prefix);
    }

    public Patient actualice(Patient patient) throws Exception {
        patientRepository.findById(patient.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return patientRepository.save(patient);
    }

    public Patient deleteElement(Long code) throws Exception{
        Patient patient = patientRepository.findById(code).orElseThrow(() -> new Exception("No se encontró entidad"));
        patientRepository.delete(patient);
        return patient;
    }
}
