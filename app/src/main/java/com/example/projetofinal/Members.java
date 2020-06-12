package com.example.projetofinal;

public class Members {
    private String Name;
    private String Data_Nascimento;
    private String Telefone;
    private String Email;
    private String Localização;
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Members() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getData_Nascimento() {
        return Data_Nascimento;
    }

    public void setData_Nascimento(String data_Nascimento) {
        Data_Nascimento = data_Nascimento;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocalização() {
        return Localização;
    }

    public void setLocalização(String localização) {
        Localização = localização;
    }
}
