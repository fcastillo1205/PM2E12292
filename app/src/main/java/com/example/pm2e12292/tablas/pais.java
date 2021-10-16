package com.example.pm2e12292.tablas;

public class pais {
    private Integer idPais;
    private String nombrePais;
    private String codigoPais;

    //primer constructor
    public pais(Integer idPais, String nombrePais, String codigoPais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
        this.codigoPais = codigoPais;
    }

    //Segundo contructor
    public pais(){

    }

    //setters
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }


    //getters
    public String getNombrePais() {
        return nombrePais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public String getCodigoPais() {
        return codigoPais;
    }


}
