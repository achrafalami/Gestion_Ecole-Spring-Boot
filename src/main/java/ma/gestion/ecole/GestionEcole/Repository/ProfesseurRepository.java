package ma.gestion.ecole.GestionEcole.Repository;

import ma.gestion.ecole.GestionEcole.Entity.Professeur.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}
