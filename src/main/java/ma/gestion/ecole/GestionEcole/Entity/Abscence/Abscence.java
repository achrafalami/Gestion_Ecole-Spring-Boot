package ma.gestion.ecole.GestionEcole.Entity.Abscence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Enum.NomMatiere;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Abscence{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private NomMatiere nomMatiere;
    private LocalDateTime date;
    private String motif;
}
