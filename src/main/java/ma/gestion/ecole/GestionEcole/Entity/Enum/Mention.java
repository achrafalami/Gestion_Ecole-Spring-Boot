package ma.gestion.ecole.GestionEcole.Entity.Enum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public enum Mention {
    TRES_BIEN("Tres bien"), BIEN("Bien"), ASSEZ_BIEN("Assez bien"), PASSABLE("Passable"), INSUFFISANT("Insuffisant");
    String libelle;
    Mention(String libelle)
    {
        this.libelle = libelle;
    }
}
