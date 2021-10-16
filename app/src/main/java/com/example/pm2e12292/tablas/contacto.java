package com.example.pm2e12292.tablas;

public class contacto {
    private Integer idContacto;
    private String contactoPais;
    private String contactoNombre;
    private String contactoNumero;
    private String contactoNota;

    public contacto(Integer idContacto, String contactoPais, String contactoNombre, String contactoNumero, String contactoNota) {
        this.idContacto = idContacto;
        this.contactoPais = contactoPais;
        this.contactoNombre = contactoNombre;
        this.contactoNumero = contactoNumero;
        this.contactoNota = contactoNota;
    }
    public contacto(){

    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getContactoPais() {
        return contactoPais;
    }

    public void setContactoPais(String contactoPais) {
        this.contactoPais = contactoPais;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoNota() {
        return contactoNota;
    }

    public void setContactoNota(String contactoNota) {
        this.contactoNota = contactoNota;
    }

    public String getContactoNumero() {
        return contactoNumero;
    }

    public void setContactoNumero(String contactoNumero) {
        this.contactoNumero = contactoNumero;
    }
}
