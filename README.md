package com.vacunacion;

public class Ciudadano {
    private String nombre;

    public Ciudadano(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
