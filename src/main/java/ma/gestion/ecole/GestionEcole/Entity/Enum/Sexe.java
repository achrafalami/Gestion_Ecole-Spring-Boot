package ma.gestion.ecole.GestionEcole.Entity.Enum;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public enum Sexe {
    HOMME("Homme"), FEMME("Femme");

    String libelle;
    Sexe(String libelle)
    {
        this.libelle = libelle;
    }

}
