package entity;

import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String navn;

    @OneToMany(mappedBy = "avdeling")
    private List<Ansatt> ansatte;

    @OneToOne
    @JoinColumn(name = "sjef_id", nullable = false, unique = true)
    private Ansatt sjef;
}
