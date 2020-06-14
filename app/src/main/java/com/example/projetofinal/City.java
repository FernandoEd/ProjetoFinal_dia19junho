package com.example.projetofinal;

public class City {
    private String Name;

    public City(String name) {
        Name = name;
    }

    public City() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) {
        Codigo_Postal = codigo_Postal;
    }

    private String Codigo_Postal;


}
