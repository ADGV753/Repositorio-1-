package com.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User{
    private int ID_Usuario;
    private int CC;
    private String Nombre;
    private String Email;
    private String Contraseña;
    private String Rol;

    // Constructor vacío

    public User() {}

    public User(int ID_Usuario, int CC, String nombre, String email, String contraseña, String rol) {
        this.ID_Usuario = ID_Usuario;
        this.CC = CC;
        Nombre = nombre;
        Email = email;
        Contraseña = contraseña;
        Rol = rol;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID_Usuario=" + ID_Usuario +
                ", CC=" + CC +
                ", Nombre='" + Nombre + '\'' +
                ", Email='" + Email + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", Rol='" + Rol + '\'' +
                '}';
    }
}



