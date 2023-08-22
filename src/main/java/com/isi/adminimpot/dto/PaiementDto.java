package com.isi.adminimpot.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDto {
    private Long id;
    @NotNull(message = "la date de paiement ne doit pas être null")
    private Date datePaiement;
    @NotNull(message = "le montant de paiement ne doit pas être null")
    private double montantPaiement;
    private Long declarationId;
}
