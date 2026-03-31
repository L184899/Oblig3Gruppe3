package entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "ansatt")
public class Ansatt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ansatt_id;

    @Column(unique = true, nullable = false, length = 4)
    private String brukernavn;

    private String fornavn;
    private String etternavn;
    private String stilling;

    @Column(name = "ansettelsesdato")
    private LocalDate ansettelsesdato;

    private BigDecimal maanedslonn;

    @ManyToOne
    @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    @OneToMany(mappedBy = "ansatt")
    private List<Deltakelse> deltakelser;

    public Ansatt() {
    }

    public Ansatt(int ansatt_id, String brukernavn, String fornavn, String etternavn,
                  LocalDate ansettelsesdato, String stilling, BigDecimal maanedslonn) {
        this.ansatt_id = ansatt_id;
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsesdato = ansettelsesdato;
        this.stilling = stilling;
        this.maanedslonn = maanedslonn;
    }

    public int getAnsattId() {
        return ansatt_id;
    }

    public void setAnsattId(int ansattId) {
        this.ansatt_id = ansattId;
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

    public BigDecimal getMaanedslonn() {
        return maanedslonn;
    }

    public void setMaanedslonn(BigDecimal maanedslonn) {
        this.maanedslonn = maanedslonn;
    }

    public Avdeling getAvdeling() {
        return avdeling;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    public List<Deltakelse> getDeltakelser() {
        return deltakelser;
    }

    public void setDeltakelser(List<Deltakelse> deltakelser) {
        this.deltakelser = deltakelser;
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "ansattId=" + ansatt_id +
                ", brukernavn='" + brukernavn + '\'' +
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", ansettelsesdato=" + ansettelsesdato +
                ", stilling='" + stilling + '\'' +
                ", maanedslonn=" + maanedslonn +
                '}';
    }
}