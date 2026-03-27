package entity;

import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String navn;
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt")
    private List<Deltakelse> deltakelser;
}
