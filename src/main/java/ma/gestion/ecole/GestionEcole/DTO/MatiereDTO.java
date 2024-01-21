package ma.gestion.ecole.GestionEcole.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Enum.NomMatiere;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatiereDTO {
    private Long id;

    private NomMatiere nom;
    private Double note;

    private int coefficient;
}
