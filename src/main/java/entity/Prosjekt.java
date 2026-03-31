package entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "prosjekt")
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjekt_id;

    private String navn;
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt")
    private List<Deltakelse> deltakelser;

    public Prosjekt() {
    }

    public int getProsjekt_id() {
        return prosjekt_id;
    }

    public void setProsjekt_id(int prosjekt_id) {
        this.prosjekt_id = prosjekt_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public List<Deltakelse> getDeltakelser() {
        return deltakelser;
    }

    public void setDeltakelser(List<Deltakelse> deltakelser) {
        this.deltakelser = deltakelser;
    }

    @Override
    public String toString() {
        return "Prosjekt{" +
                "prosjekt_id=" + prosjekt_id +
                ", navn='" + navn + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                '}';
    }
}