package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.TuteurDTO;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Matiere;
import ma.gestion.ecole.GestionEcole.Entity.Tuteur;
import ma.gestion.ecole.GestionEcole.Exception.UserAlreadyExistException;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Repository.EleveRepository;
import ma.gestion.ecole.GestionEcole.Repository.TuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TuteurServiceImpl implements ITuteurService {

    @Autowired
    private TuteurRepository tuteurRepository;
    @Autowired
    private EleveRepository eleveRepository;
    @Override
    public TuteurDTO findTuteurById(Long id) {
        Tuteur tuteur = new Tuteur();
        tuteur=tuteurRepository.findById(id).get();
        return mapToDTO(tuteur);
    }

    @Override
    public List<TuteurDTO> findAll() {
        List<Tuteur> tuteurList = new ArrayList<>();
        tuteurList = tuteurRepository.findAll();
        return mapListToDTO(tuteurList);
    }

    @Override
    public void create(Tuteur tuteur) {
        if (tuteurRepository.findById(tuteur.getId()).isPresent()){
            new UserAlreadyExistException("Ce Tuteur exist deja");
        }
        tuteurRepository.save(tuteur);
    }

    @Override
    public void update(Long id, Tuteur tuteur) {
        Optional<Tuteur> existingTuteurOptional = tuteurRepository.findById(id);
        if (existingTuteurOptional.isPresent()) {
            Tuteur existingTuteur = existingTuteurOptional.get();
            existingTuteur.setLogin(tuteur.getLogin());
            existingTuteur.setPass(tuteur.getPass());
            existingTuteur.setName(tuteur.getName());
            existingTuteur.setPrenom(tuteur.getPrenom());
            existingTuteur.setImage(tuteur.getImage());
            existingTuteur.setAdresse(tuteur.getAdresse());
            existingTuteur.setLieu(tuteur.getLieu());
            existingTuteur.setEmail(tuteur.getEmail());
            existingTuteur.setTelephone(tuteur.getTelephone());
            existingTuteur.setSexe(tuteur.getSexe());
            existingTuteur.setRole(tuteur.getRole());

            tuteurRepository.save(existingTuteur);
        } else {
            throw new UserNotFoundException("Tuteur n'existe pas contenant l'id :" + id);
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Tuteur> existingTuteurOptional = tuteurRepository.findById(id);
        if (existingTuteurOptional.isPresent()) {
            tuteurRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Tuteur n'existe pas contenant l'id :" + id);
        }

    }
//    @Override
//    public void assignerElevesTuteur(Long tuteurId, List<Long> EleveIds) {
//        Tuteur tuteur = tuteurRepository.findById(tuteurId)
//                .orElseThrow(() -> new UserNotFoundException("Tuteur non trouvé avec l'ID : " + tuteurId));
//
//        List<Eleve> eleveList = eleveRepository.findAllById(EleveIds);
//
//        tuteur.setEleveList(eleveList);
//
//        tuteurRepository.save(tuteur);
//    }
    private TuteurDTO mapToDTO(Tuteur tuteur) {
        return TuteurDTO.builder()
                .id(tuteur.getId())
                .name(tuteur.getName())
                .prenom(tuteur.getPrenom())
                .pass(tuteur.getPass())
                .login(tuteur.getLogin())
                .adresse(tuteur.getAdresse())
                .lieu(tuteur.getLieu())
                .email(tuteur.getEmail())
                .image(tuteur.getImage())
                .role(tuteur.getRole())
                .sexe(tuteur.getSexe())
                .telephone(tuteur.getTelephone())
                .build();
    }
    private List<TuteurDTO> mapListToDTO(List<Tuteur> tuteurList) {
        return tuteurList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
