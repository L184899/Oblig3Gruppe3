package entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
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

    public int getAvdeling_id() { return avdeling_id; }
    public void setAvdeling_id(int avdeling_id) { this.avdeling_id = avdeling_id; }

    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public Ansatt getSjef() { return sjef; }
    public void setSjef(Ansatt sjef) { this.sjef = sjef; }
}
