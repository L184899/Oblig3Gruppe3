package entity;

import java.util.List;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Deltakelse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ansatt_id", nullable = false)
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjekt_id", nullable = false)
    private Prosjekt prosjekt;

    private String rolle;
    private int timer;
}
