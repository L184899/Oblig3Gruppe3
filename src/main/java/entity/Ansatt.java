package entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ansatt")
public class Ansatt {

    @Id
    @Column(name = "ansatt_id")
    private int ansattId;

    @Column(name = "brukernavn", nullable = false, unique = true)
    private String brukernavn;

    @Column(name = "fornavn", nullable = false)
    private String fornavn;

    @Column(name = "etternavn", nullable = false)
    private String etternavn;

    @Column(name = "ansettelsesdato", nullable = false)
    private LocalDate ansettelsesdato;

    @Column(name = "stilling", nullable = false)
    private String stilling;

    @Column(name = "maanedslonn", nullable = false)
    private double maanedslonn;

    public Ansatt() {
    }

    public Ansatt(int ansattId, String brukernavn, String fornavn, String etternavn,
                  LocalDate ansettelsesdato, String stilling, double maanedslonn) {
        this.ansattId = ansattId;
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsesdato = ansettelsesdato;
        this.stilling = stilling;
        this.maanedslonn = maanedslonn;
    }

    public int getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(int ansattId) {
        this.ansattId = ansattId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getAnsettelsesdato() {
        return ansettelsesdato;
    }

    public void setAnsettelsesdato(LocalDate ansettelsesdato) {
        this.ansettelsesdato = ansettelsesdato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public double getMaanedslonn() {
        return maanedslonn;
    }

    public void setMaanedslonn(double maanedslonn) {
        this.maanedslonn = maanedslonn;
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "ansattId=" + ansattId +
                ", brukernavn='" + brukernavn + '\'' +
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", ansettelsesdato=" + ansettelsesdato +
                ", stilling='" + stilling + '\'' +
                ", maanedslonn=" + maanedslonn +
                '}';
    }
}