package ma.gestion.ecole.GestionEcole.Controller;

import ma.gestion.ecole.GestionEcole.DTO.MatiereDTO;
import ma.gestion.ecole.GestionEcole.Entity.Matiere;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Service.MatiereServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gestioneMatiere")
public class MatiereController {
    @Autowired
    private MatiereServiceImpl matiereService;

    @GetMapping(value = "/matieres/{id}")
    public MatiereDTO findMatiereById(@PathVariable(name = "id") Long id) {
        return matiereService.findMatiereById(id);
    }

    @GetMapping(value = "/matieres")
    public List<MatiereDTO> findAll() {
        return matiereService.findAll();
    }
    @PostMapping(value = "/matieres")
    public ResponseEntity<Object> create(@Validated @RequestBody Matiere matiere) {
        if (matiereService.findAll().stream().filter(a->a.getId()==matiere.getId()).findFirst().isPresent()){
            return ResponseEntity.notFound().build();
        }
        matiereService.create(matiere);
        return new ResponseEntity<>("Matiere cree avec succes", HttpStatus.CREATED);
    }
    @PutMapping(value = "/matieres/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody Matiere matiere) {
        try {
            matiereService.update(id, matiere);
            return new ResponseEntity<>("Matiere Modifiee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/matieres/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
        try {
            matiereService.delete(id);
            return new ResponseEntity<>("Eleve supprimee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
