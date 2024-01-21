package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.EleveDTO;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;

import java.util.List;

public interface IEleveService {
    EleveDTO findEleveById(Long Id);
    List<EleveDTO> findAll();
    void create(Eleve eleve);

    void update(Long id, Eleve eleve);

    void delete(Long id);
    void assignerMatieresEleve(Long eleveId, List<Long> matiereIds);
}
