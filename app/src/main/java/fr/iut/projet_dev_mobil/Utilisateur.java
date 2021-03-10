package fr.iut.projet_dev_mobil;

import java.sql.Date;

public class Utilisateur {
    private String Nom,Prenom,DateNaiss,Email,Password,VerifPassword;
    private Boolean Sexe;

    public Utilisateur() {
    }

    public String getNom() {
        return Nom;
    }

    public boolean setNom(String nom) {
        if (nom.isEmpty()){
            return false;
        }
        else {
            Nom = nom;
            return true;
        }
    }

    public String getPrenom() {
        return Prenom;
    }

    public boolean setPrenom(String prenom) {
        if (prenom.isEmpty()){
            return false;
        }
        else {
            Prenom = prenom;
            return true;
        }
    }

    public String getEmail() {
        return Email;
    }

    public boolean setEmail(String email) {
        if (email.isEmpty()){
            return false;
        }
        else {
            Email = email;
            return true;
        }
    }

    public String getPassword() {
        return Password;
    }

    public boolean setPassword(String password) {
        if (password.isEmpty()){
            return false;
        }
        else {
            Password = password;
            return true;
        }
    }

    public String getVerifPassword() {
        return VerifPassword;
    }

    public boolean setVerifPassword(String verifPassword) {
        if (verifPassword.isEmpty()){
            return false;
        }
        else {
            VerifPassword = verifPassword;
            return true;
        }
    }

    public String getDateNaiss() {
        return DateNaiss;
    }

    public boolean setDateNaiss(String dateNaiss) {
        if (dateNaiss.isEmpty()){
            return false;
        }
        else {
            DateNaiss = dateNaiss;
            return true;
        }
    }

    public boolean isValidPassword(String password, String verifPassword){
        if (password.equals(verifPassword)){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean getSexe() {
        return Sexe;
    }

    public void setSexe(Boolean sexe) {
        Sexe = sexe;
    }
}
