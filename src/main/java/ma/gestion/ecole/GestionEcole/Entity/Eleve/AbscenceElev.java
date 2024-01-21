package ma.gestion.ecole.GestionEcole.Entity.Eleve;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Abscence.Abscence;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AbscenceElev extends Abscence {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleve_id")
    private Eleve eleve;
}
