package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deltakelse")
public class Deltakelse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deltakelse_id;

    @ManyToOne
    @JoinColumn(name = "ansatt_id", nullable = false)
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjekt_id", nullable = false)
    private Prosjekt prosjekt;

    private String rolle;
    private int timer;

    public Deltakelse() {
    }

    public int getDeltakelse_id() {
        return deltakelse_id;
    }

    public void setDeltakelse_id(int deltakelse_id) {
        this.deltakelse_id = deltakelse_id;
    }

    public Ansatt getAnsatt() {
        return ansatt;
    }

    public void setAnsatt(Ansatt ansatt) {
        this.ansatt = ansatt;
    }

    public Prosjekt getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(Prosjekt prosjekt) {
        this.prosjekt = prosjekt;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "Deltakelse{" +
                "deltakelse_id=" + deltakelse_id +
                ", ansatt='" + (ansatt != null ? ansatt.getFornavn() + " " + ansatt.getEtternavn() : "ukjent") + '\'' +
                ", prosjekt='" + (prosjekt != null ? prosjekt.getNavn() : "ukjent") + '\'' +
                ", rolle='" + rolle + '\'' +
                ", timer=" + timer +
                '}';
    }
}