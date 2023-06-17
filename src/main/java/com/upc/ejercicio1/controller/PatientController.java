package com.upc.ejercicio1.controller;

import com.upc.ejercicio1.business.PatientBusiness;
import com.upc.ejercicio1.dtos.PatientDTO;
import com.upc.ejercicio1.entitys.Patient;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    public PatientBusiness business;
    Logger logger = LoggerFactory.getLogger(PatientController.class);
    @GetMapping("/message")
    public String Mensaje(){
        return "si funciona :D";
    }
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> listado(){
        List<Patient> list = business.list();
        List<PatientDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<PatientDTO>>(listDto, HttpStatus.OK);
    }

    @GetMapping("/patient/{codigo}")
    public ResponseEntity<PatientDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        Patient patient;
        PatientDTO patientDTO;
        try {
            logger.debug("Buscando entidad");
            patient = business.findfunction(codigo);
            patientDTO = convertToDto(patient);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<PatientDTO>(patientDTO, HttpStatus.OK);
    }

    @PostMapping("/patiente")
    public ResponseEntity<PatientDTO> insertar(@RequestBody PatientDTO patientDTO) {
        Patient patient = convertToEntity(patientDTO);
        try {
            logger.debug("Creando objeto");
            patient= business.register(patient);
            patientDTO= convertToDto(patient);

        }catch(Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<PatientDTO>(patientDTO, HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<PatientDTO> borrarAutor(@PathVariable(value = "codigo") Long codigo){
        Patient  patient;
        PatientDTO patientDTO;
        try {
            patient = business.deleteElement(codigo);
            logger.debug("Eliminando objeto");
            patientDTO = convertToDto(patient);
            return new ResponseEntity<PatientDTO>(patientDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Eliminación ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }


    private PatientDTO convertToDto(Patient patient) {
        ModelMapper modelMapper = new ModelMapper();
        PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
        return patientDTO;
    }

    private Patient convertToEntity(PatientDTO patientDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Patient post = modelMapper.map(patientDTO, Patient.class);
        return post;
    }

    private List<PatientDTO> convertToLisDto(List<Patient> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
