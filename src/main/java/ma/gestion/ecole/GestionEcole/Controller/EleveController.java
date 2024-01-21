package ma.gestion.ecole.GestionEcole.Controller;

import ma.gestion.ecole.GestionEcole.DTO.EleveDTO;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;
import ma.gestion.ecole.GestionEcole.Exception.UserNotFoundException;
import ma.gestion.ecole.GestionEcole.Service.EleveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/gestioneleve")
public class EleveController {
    @Autowired
    private EleveServiceImpl eleveService;

    @GetMapping(value = "/eleves/{id}")
    public EleveDTO findEleveById(@PathVariable(name = "id") Long id) {
        return eleveService.findEleveById(id);
    }

    @GetMapping(value = "/eleves")
    public List<EleveDTO> findAll() {
        return eleveService.findAll();
    }
    @PostMapping(value = "/eleves")
    public ResponseEntity<Object> create(@Validated @RequestBody Eleve eleve) {
        if (eleveService.findAll().stream().filter(a->a.getId()==eleve.getId()).findFirst().isPresent()){
            return ResponseEntity.notFound().build();
        }
        eleveService.create(eleve);
        return new ResponseEntity<>("Eleve cree avec succes", HttpStatus.CREATED);
    }
    @PutMapping(value = "/eleves/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody Eleve eleve) {
        try {
            eleveService.update(id, eleve);
            return new ResponseEntity<>("Eleve Modifiee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/eleves/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
        try {
            eleveService.delete(id);
            return new ResponseEntity<>("Eleve ssupprimee avec succes", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/eleves/{id}/assign-matieres")
    public ResponseEntity<Object> assignerMatieres(@PathVariable(name = "id") Long id, @RequestBody List<Long> matiereIds) {
        try {
            eleveService.assignerMatieresEleve(id, matiereIds);
            return new ResponseEntity<>("Matieres assignées avec succès à l'élève", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
