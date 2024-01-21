package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.TuteurDTO;
import ma.gestion.ecole.GestionEcole.Entity.Tuteur;

import java.util.List;

public interface ITuteurService {

    TuteurDTO findTuteurById(Long Id);
    List<TuteurDTO> findAll();
    void create(Tuteur tuteur);

    void update(Long id, Tuteur tuteur);

    void delete(Long id);
//    void assignerElevesTuteur(Long tuteurId, List<Long> EleveIds);
}
