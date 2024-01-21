package ma.gestion.ecole.GestionEcole.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Professeur.Professeur;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Admin extends User {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tuteur> tuteurList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Eleve> eleveList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Professeur> professeurList;
}
