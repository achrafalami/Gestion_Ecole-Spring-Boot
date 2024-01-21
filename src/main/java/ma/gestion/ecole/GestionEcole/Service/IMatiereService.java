package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.EleveDTO;
import ma.gestion.ecole.GestionEcole.DTO.MatiereDTO;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Matiere;

import java.util.List;

public interface IMatiereService {
    MatiereDTO findMatiereById(Long Id);
    List<MatiereDTO> findAll();

    void create(Matiere matiere);

    void update(Long id, Matiere matiere);

    void delete(Long id);
}
