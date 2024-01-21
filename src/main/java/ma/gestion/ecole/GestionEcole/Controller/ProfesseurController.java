package ma.gestion.ecole.GestionEcole.Controller;

import ma.gestion.ecole.GestionEcole.DTO.ProfesseurDTO;
import ma.gestion.ecole.GestionEcole.Entity.Professeur.Professeur;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Service.ProfesseurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gestionProfesseur")
public class ProfesseurController {

    @Autowired
    private ProfesseurServiceImpl professeurService;

    @GetMapping(value = "/professeurs/{id}")
    public ProfesseurDTO findProfesseurById(@PathVariable(name = "id") Long id) {

        return professeurService.findProfesseurById(id);
    }
    @GetMapping(value = "/professeurs")
    public List<ProfesseurDTO> findAll() {

        return professeurService.findAll();
    }
    @PostMapping(value = "/professeurs")
    public ResponseEntity<Object> create(@Validated @RequestBody Professeur professeur) {
        if (professeurService.findAll().stream().filter(a->a.getId()==professeur.getId()).findFirst().isPresent()){
            return ResponseEntity.notFound().build();
        }
        professeurService.create(professeur);
        return new ResponseEntity<>("Professeur cree avec succes", HttpStatus.CREATED);
    }
    @PutMapping(value = "/professeurs/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody Professeur professeur) {
        try {
            professeurService.update(id, professeur);
            return new ResponseEntity<>("Professeur modifiee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/professeurs/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
        try {
            professeurService.delete(id);
            return new ResponseEntity<>("Professeur supprimee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/professeurs/{id}/assign-matiere")
    public ResponseEntity<Object> assignerMatieresProfesseur(@PathVariable(name = "id") Long idProfeseur, @RequestBody Long idMatiere) {
        try {
            professeurService.assignerMatieresProfesseur(idProfeseur, idMatiere);
            return new ResponseEntity<>("Matieres assignées avec succès au professeur", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}