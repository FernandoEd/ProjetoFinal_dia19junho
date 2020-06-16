package com.example.projetofinal;

public class CafeBar {
    private String Name;
    private String Localidade;

    public String getRuaCafeBar() {
        return RuaCafeBar;
    }

    public void setRuaCafeBar(String ruaCafeBar) {
        RuaCafeBar = ruaCafeBar;
    }

    public String getHorarioCafeBar() {
        return HorarioCafeBar;
    }

    public void setHorarioCafeBar(String horarioCafeBar) {
        HorarioCafeBar = horarioCafeBar;
    }

    private String RuaCafeBar;
    private String HorarioCafeBar;

    public CafeBar() {
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


}
