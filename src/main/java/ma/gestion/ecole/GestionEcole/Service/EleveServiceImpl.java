package ma.gestion.ecole.GestionEcole.Service;

import ma.gestion.ecole.GestionEcole.DTO.EleveDTO;
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
public class EleveServiceImpl implements IEleveService{
    @Autowired
    private EleveRepository eleveRepository;
    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public EleveDTO findEleveById(Long id) {
        Eleve eleve = new Eleve();
        eleve=eleveRepository.findById(id).get();
        return mapToDTO(eleve);
    }

    @Override
    public List<EleveDTO> findAll() {
        List<Eleve> eleveList = new ArrayList<>();
        eleveList = eleveRepository.findAll();
        return mapListToDTO(eleveList);
    }

    @Override
    public void create(Eleve eleve) {
        if (eleveRepository.findById(eleve.getId()).isPresent()){
            new UserAlreadyExistException("Ce eleve exist deja");
        }
        eleveRepository.save(eleve);
    }

    @Override
    public void update(Long id, Eleve eleve) {
        Optional<Eleve> existingEleveOptional = eleveRepository.findById(id);
        if (existingEleveOptional.isPresent()) {
            Eleve existingEleve = existingEleveOptional.get();
            existingEleve.setLogin(eleve.getLogin());
            existingEleve.setPass(eleve.getPass());
            existingEleve.setName(eleve.getName());
            existingEleve.setPrenom(eleve.getPrenom());
            existingEleve.setImage(eleve.getImage());
            existingEleve.setAdresse(eleve.getAdresse());
            existingEleve.setLieu(eleve.getLieu());
            existingEleve.setEmail(eleve.getEmail());
            existingEleve.setTelephone(eleve.getTelephone());
            existingEleve.setSexe(eleve.getSexe());
            existingEleve.setRole(eleve.getRole());

            eleveRepository.save(existingEleve);
        } else {
            throw new UserNotFoundException("Eleve n'existe pas contenant l'id :" + id);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Eleve> existingEleveOptional = eleveRepository.findById(id);
        if (existingEleveOptional.isPresent()) {
            eleveRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Eleve n'existe pas contenant l'id :" + id);
        }
    }
    @Override
    public void assignerMatieresEleve(Long eleveId, List<Long> matiereIds) {
        Eleve eleve = eleveRepository.findById(eleveId)
                .orElseThrow(() -> new UserNotFoundException("Eleve non trouvé avec l'ID : " + eleveId));

        List<Matiere> matieres = matiereRepository.findAllById(matiereIds);

        eleve.setMatieres(matieres);

        eleveRepository.save(eleve);
    }

    private EleveDTO mapToDTO(Eleve eleve) {
        return EleveDTO.builder()
                .id(eleve.getId())
                .name(eleve.getName())
                .prenom(eleve.getPrenom())
                .pass(eleve.getPass())
                .login(eleve.getLogin())
                .adresse(eleve.getAdresse())
                .email(eleve.getEmail())
                .image(eleve.getImage())
                .lieu(eleve.getLieu())
                .role(eleve.getRole())
                .sexe(eleve.getSexe())
                .telephone(eleve.getTelephone())
                .build();
    }
    private List<EleveDTO> mapListToDTO(List<Eleve> eleveList) {
        return eleveList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
