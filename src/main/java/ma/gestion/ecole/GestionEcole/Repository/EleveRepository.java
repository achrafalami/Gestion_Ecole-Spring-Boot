package ma.gestion.ecole.GestionEcole.Repository;

import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveRepository extends JpaRepository<Eleve,Long> {
}
