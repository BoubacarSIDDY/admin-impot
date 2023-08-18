package com.isi.adminimpot.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date datePaiement;
    private double montantPaiement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_declaration")
    private Declaration declaration;

}
