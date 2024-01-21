package ma.gestion.ecole.GestionEcole.Controller;

import ma.gestion.ecole.GestionEcole.DTO.TuteurDTO;
import ma.gestion.ecole.GestionEcole.Entity.Tuteur;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Service.TuteurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/gestionTuteur")
public class TuteurController {

    @Autowired
    private TuteurServiceImpl tuteurService;

    @GetMapping(value = "/tuteurs/{id}")
    public TuteurDTO findTuteurById(@PathVariable(name = "id") Long id) {

        return tuteurService.findTuteurById(id);
    }

    @GetMapping(value = "/tuteurs")
    public List<TuteurDTO> findAll() {

        return tuteurService.findAll();
    }

    @PostMapping(value = "/tuteurs")
    public ResponseEntity<Object> create(@Validated @RequestBody Tuteur tuteur) {
        if (tuteurService.findAll().stream().filter(a->a.getId()==tuteur.getId()).findFirst().isPresent()){
            return ResponseEntity.notFound().build();
        }
        tuteurService.create(tuteur);
        return new ResponseEntity<>("Tuteur cree avec succes", HttpStatus.CREATED);
    }
    @PutMapping(value = "/tuteurs/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody Tuteur tuteur) {
        try {
            tuteurService.update(id, tuteur);
            return new ResponseEntity<>("Tuteur modifiee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/tuteurs/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
        try {
            tuteurService.delete(id);
            return new ResponseEntity<>("Tuteur supprimee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
//    @PutMapping(value = "/tuteurs/{id}/assign-eleves")
//    public ResponseEntity<Object> assignerElevesTuteur(@PathVariable(name = "id") Long id, @RequestBody List<Long> elevesIds) {
//        try {
//            tuteurService.assignerElevesTuteur(id, elevesIds);
//            return new ResponseEntity<>("Eleves assignées avec succès à l'élève", HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
