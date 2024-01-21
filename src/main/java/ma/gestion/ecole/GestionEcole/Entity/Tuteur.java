package ma.gestion.ecole.GestionEcole.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Eleve.Eleve;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Tuteur extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @OneToMany(mappedBy = "tuteur", cascade = CascadeType.ALL)
    private List<Eleve> eleveList;
}
