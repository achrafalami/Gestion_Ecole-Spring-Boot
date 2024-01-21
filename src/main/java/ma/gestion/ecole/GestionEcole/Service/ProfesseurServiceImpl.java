package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.ProfesseurDTO;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Entity.Matiere;
import ma.gestion.ecole.GestionEcole.Entity.Professeur.Professeur;
import ma.gestion.ecole.GestionEcole.Exception.UserAlreadyExistException;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Repository.MatiereRepository;
import ma.gestion.ecole.GestionEcole.Repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesseurServiceImpl implements IProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private MatiereRepository matiereRepository;
    @Override
    public ProfesseurDTO findProfesseurById(Long id) {
        Professeur professeur = new Professeur();
        professeur=professeurRepository.findById(id).get();
        return mapToDTO(professeur);
    }

    @Override
    public List<ProfesseurDTO> findAll() {
        List<Professeur> professeurList = new ArrayList<>();
        professeurList = professeurRepository.findAll();
        return mapListToDTO(professeurList);
    }

    @Override
    public void create(Professeur professeur) {
        if (professeurRepository.findById(professeur.getId()).isPresent()){
            new UserAlreadyExistException("Ce Professeur exist deja");
        }
        professeurRepository.save(professeur);
    }

    @Override
    public void update(Long id, Professeur professeur) {
        Optional<Professeur> existingProfesseurOptional = professeurRepository.findById(id);
        if (existingProfesseurOptional.isPresent()) {
            Professeur existingProfesseur = existingProfesseurOptional.get();
            existingProfesseur.setLogin(professeur.getLogin());
            existingProfesseur.setPass(professeur.getPass());
            existingProfesseur.setName(professeur.getName());
            existingProfesseur.setPrenom(professeur.getPrenom());
            existingProfesseur.setImage(professeur.getImage());
            existingProfesseur.setAdresse(professeur.getAdresse());
            existingProfesseur.setLieu(professeur.getLieu());
            existingProfesseur.setEmail(professeur.getEmail());
            existingProfesseur.setTelephone(professeur.getTelephone());
            existingProfesseur.setSexe(professeur.getSexe());
            existingProfesseur.setRole(professeur.getRole());

            professeurRepository.save(existingProfesseur);
        } else {
            throw new UserNotFoundException("Professeur n'existe pas contenant l'id :" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Professeur> existingProfesseurOptional = professeurRepository.findById(id);
        if (existingProfesseurOptional.isPresent()) {
            professeurRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Professeur n'existe pas contenant l'id :" + id);
        }
    }

    @Override
    public void assignerMatieresProfesseur(Long idProf, Long idMatiere) {
        Professeur professeur = professeurRepository.findById(idProf)
                .orElseThrow(() -> new UserNotFoundException("Professeur non trouvé avec l'ID : " + idProf));

        Matiere matiere = matiereRepository.findById(idMatiere)
                .orElseThrow(()->new UserNotFoundException("Matiere non trouvé avec l'ID : " + idMatiere));

        professeur.setMatiere(matiere);

        professeurRepository.save(professeur);
    }

    private ProfesseurDTO mapToDTO(Professeur professeur) {
        return ProfesseurDTO.builder()
                .id(professeur.getId())
                .name(professeur.getName())
                .prenom(professeur.getPrenom())
                .pass(professeur.getPass())
                .login(professeur.getLogin())
                .adresse(professeur.getAdresse())
                .lieu(professeur.getLieu())
                .email(professeur.getEmail())
                .image(professeur.getImage())
                .role(professeur.getRole())
                .sexe(professeur.getSexe())
                .telephone(professeur.getTelephone())
                .build();
    }
    private List<ProfesseurDTO> mapListToDTO(List<Professeur> professeurList) {
        return professeurList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
