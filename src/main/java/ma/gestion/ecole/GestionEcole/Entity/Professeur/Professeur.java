package ma.gestion.ecole.GestionEcole.Entity.Professeur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Admin;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.AbscenceElev;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.RetardElev;
import ma.gestion.ecole.GestionEcole.Entity.Enum.NomMatiere;
import ma.gestion.ecole.GestionEcole.Entity.Matiere;
import ma.gestion.ecole.GestionEcole.Entity.User;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Professeur extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private List<AbscenceProf> absProf;
    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private List<RetardProf> retProf;
    @OneToOne(fetch  = FetchType.LAZY)
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;
}
