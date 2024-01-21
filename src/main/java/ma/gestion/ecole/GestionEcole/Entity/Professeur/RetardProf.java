package ma.gestion.ecole.GestionEcole.Entity.Professeur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Abscence.Abscence;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RetardProf extends Abscence {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
}
