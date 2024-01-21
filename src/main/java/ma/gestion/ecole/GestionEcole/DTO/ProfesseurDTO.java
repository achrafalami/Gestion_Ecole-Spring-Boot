package ma.gestion.ecole.GestionEcole.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Enum.Role;
import ma.gestion.ecole.GestionEcole.Entity.Enum.Sexe;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfesseurDTO {
    private Long id;

    private String login;

    private String pass;

    private String name;

    private String prenom;

    private String image;

    private String adresse;

    private String lieu;

    private String email;

    private String telephone;

    private Sexe sexe;

    private Role role;

}
