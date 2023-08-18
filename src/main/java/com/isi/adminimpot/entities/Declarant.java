package com.isi.adminimpot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Declarant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String raisonSocial;
    private String adresse;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "declarant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Declaration> declarations;
}
