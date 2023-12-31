package com.isi.adminimpot.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDeclaration;
    private double montantDeclaration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_declarant")
    private Declarant declarant;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paiement> paiements;
}
