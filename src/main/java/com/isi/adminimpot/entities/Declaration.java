package com.isi.adminimpot.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity @Data
public class Declaration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDeclaration;
    private double montantDeclaration;
    private Long idDeclarant;
}
