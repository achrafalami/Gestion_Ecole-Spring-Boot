package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.EleveDTO;
import ma.gestion.ecole.GestionEcole.DTO.MatiereDTO;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Matiere;
import ma.gestion.ecole.GestionEcole.Exception.UserAlreadyExistException;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Repository.EleveRepository;
import ma.gestion.ecole.GestionEcole.Repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatiereServiceImpl implements IMatiereService{
    @Autowired
    private MatiereRepository matiereRepository;
    @Override
    public MatiereDTO findMatiereById(Long Id) {
        Matiere matiere = new Matiere();
        matiere=matiereRepository.findById(Id).get();
        return mapToDTO(matiere);
    }

    @Override
    public List<MatiereDTO> findAll() {
        List<Matiere> matiereList = new ArrayList<>();
        matiereList = matiereRepository.findAll();
        return mapListToDTO(matiereList);
    }

    @Override
    public void create(Matiere matiere) {
        if (matiereRepository.findById(matiere.getId()).isPresent()){
            new UserAlreadyExistException("Cette matiere exist deja");
        }
        matiereRepository.save(matiere);
    }

    @Override
    public void update(Long id, Matiere matiere) {
        Optional<Matiere> existingMatiereOptional = matiereRepository.findById(id);
        if (existingMatiereOptional.isPresent()) {
            Matiere existingMatiere = existingMatiereOptional.get();
            existingMatiere.setId(matiere.getId());
            existingMatiere.setCoefficient(matiere.getCoefficient());
            existingMatiere.setNom(matiere.getNom());
            existingMatiere.setNote(matiere.getNote());

            matiereRepository.save(existingMatiere);
        } else {
            throw new UserNotFoundException("Matiere n'existe pas contenant l'id :" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Matiere> existingEleveOptional = matiereRepository.findById(id);
        if (existingEleveOptional.isPresent()) {
            matiereRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Matiere n'existe pas contenant l'id :" + id);
        }
    }

    private MatiereDTO mapToDTO(Matiere matiere) {
        return MatiereDTO.builder()
                .id(matiere.getId())
                .nom(matiere.getNom())
                .coefficient(matiere.getCoefficient())
                .note(matiere.getNote())
                .build();
    }
    private List<MatiereDTO> mapListToDTO(List<Matiere> matiereList) {
        return matiereList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}
