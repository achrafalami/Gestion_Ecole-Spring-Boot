package ma.gestion.ecole.GestionEcole.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Enum.NomMatiere;
import ma.gestion.ecole.GestionEcole.Entity.Professeur.Professeur;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NomMatiere nom;
    private Double note;
    @Column(nullable = false)
    private int coefficient;
    @OneToOne(mappedBy = "matiere", cascade = CascadeType.ALL)
    private Professeur professeurResponsable;
    @ManyToMany(mappedBy = "matieres")
    private List<Eleve> eleves;
}
