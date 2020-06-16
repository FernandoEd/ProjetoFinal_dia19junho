package com.example.projetofinal;

public class Restaurante {
    private String Name;
    private String Localidade;
    private String RuaRestaurante;
    private String HorarioRestaurante;

    public Restaurante() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocalidade() {
        return Localidade;
    }

    public void setLocalidade(String localidade) {
        Localidade = localidade;
    }

    public String getRuaRestaurante() {
        return RuaRestaurante;
    }

    public void setRuaRestaurante(String ruaRestaurante) {
        RuaRestaurante = ruaRestaurante;
    }

    public String getHorarioRestaurante() {
        return HorarioRestaurante;
    }

    public void setHorarioRestaurante(String horarioRestaurante) {
        HorarioRestaurante = horarioRestaurante;
    }
}
