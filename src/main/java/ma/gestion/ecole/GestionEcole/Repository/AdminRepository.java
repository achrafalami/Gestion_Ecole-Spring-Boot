package ma.gestion.ecole.GestionEcole.Repository;

import ma.gestion.ecole.GestionEcole.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
