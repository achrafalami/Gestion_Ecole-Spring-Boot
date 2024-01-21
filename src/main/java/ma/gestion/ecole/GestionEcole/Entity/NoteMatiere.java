package ma.gestion.ecole.GestionEcole.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class NoteMatiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eleve_id", nullable = false)
    private Eleve eleve;

    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;

    private double note;
}
