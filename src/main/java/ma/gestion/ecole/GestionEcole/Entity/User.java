package ma.gestion.ecole.GestionEcole.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gestion.ecole.GestionEcole.Entity.Enum.Role;
import ma.gestion.ecole.GestionEcole.Entity.Enum.Sexe;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String pass;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String lieu;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}