package com.isi.adminimpot.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationDto {
    private Long id;
    @NotNull(message = "la date de declaration ne doit pas être null")
    private Date dateDeclaration;
    @NotNull(message = "le montant de declaration ne doit pas être null")
    private double montantDeclaration;
}
