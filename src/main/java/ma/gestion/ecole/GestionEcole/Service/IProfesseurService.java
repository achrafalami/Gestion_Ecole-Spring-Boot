package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.ProfesseurDTO;
import ma.gestion.ecole.GestionEcole.Entity.Professeur.Professeur;

import java.util.List;

public interface IProfesseurService {

    ProfesseurDTO findProfesseurById(Long Id);
    List<ProfesseurDTO> findAll();
    void create(Professeur professeur);

    void update(Long id, Professeur professeur);

    void delete(Long id);
    void assignerMatieresProfesseur(Long idProf , Long idMatiere);
}
