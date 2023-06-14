package com.upc.ejercicio1.entitys;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    private LocalDate appointment;
    @Column(name = "email",length = 35,nullable = false)
    private String email;
    private Type type;
    public Patient() {
    }

    public Patient(Long id, String name, LocalDate appointment, String email, Type type) {
        this.id = id;
        this.name = name;
        this.appointment = appointment;
        this.email = email;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAppointment() {
        return appointment;
    }

    public void setAppointment(LocalDate appointment) {
        this.appointment = appointment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appointment=" + appointment +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
