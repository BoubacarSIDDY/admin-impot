package com.isi.adminimpot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclarantDto {
   private Long id;
    @NotNull(message = "la raison sociale ne doit pas être null")
    private String raisonSocial;
    @NotNull(message = "l'adresse ne doit pas être null")
    private String adresse;
    @NotNull(message = "l'email ne doit pas être null")
    private String email;
    @NotNull(message = "le numéro de téléphone ne doit pas être null")
    private String telephone;
}
