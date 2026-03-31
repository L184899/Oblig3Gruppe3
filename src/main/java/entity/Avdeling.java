package entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "avdeling")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdeling_id;

    private String navn;

    @OneToMany(mappedBy = "avdeling")
    private List<Ansatt> ansatte;

    @OneToOne
    @JoinColumn(name = "sjef_id", nullable = false, unique = true)
    private Ansatt sjef;

    public Avdeling() {
    }

    public int getAvdeling_id() {
        return avdeling_id;
    }

    public void setAvdeling_id(int avdeling_id) {
        this.avdeling_id = avdeling_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public void setAnsatte(List<Ansatt> ansatte) {
        this.ansatte = ansatte;
    }

    public Ansatt getSjef() {
        return sjef;
    }

    public void setSjef(Ansatt sjef) {
        this.sjef = sjef;
    }

    @Override
    public String toString() {
        return "Avdeling{" +
                "avdeling_id=" + avdeling_id +
                ", navn='" + navn + '\'' +
                ", sjef='" + (sjef != null ? sjef.getFornavn() + " " + sjef.getEtternavn() : "ingen") + '\'' +
                '}';
    }
}