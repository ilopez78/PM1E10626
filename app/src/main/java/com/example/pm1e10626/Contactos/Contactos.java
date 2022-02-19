package com.example.pm1e10626.Contactos;

public class Contactos {
    private Integer id;
    private String pais;
    private String nombre;
    private String telefono;
    private String nota;
    private Integer codigo;


    public Contactos(String pais, Integer id, String nombre, String telefono, String nota, Integer codigo) {
        this.id = id;
        this.pais = pais;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nota = nota;
        this.codigo = codigo;
    }//Conatactos con parametros

    public Contactos(){

    }//constructor vacio

    public Contactos(String valueOf, int parseInt) {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer setCodigo(Integer codigo) {
        this.codigo = codigo;
        return codigo;
    }
}

