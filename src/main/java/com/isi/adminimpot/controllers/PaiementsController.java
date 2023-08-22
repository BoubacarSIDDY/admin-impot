package com.isi.adminimpot.controllers;

import com.isi.adminimpot.dto.PaiementDto;
import com.isi.adminimpot.service.PaiementService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API REST (Paiements)
 */
@RestController
@RequestMapping("/paiement")
@AllArgsConstructor @NoArgsConstructor
public class PaiementsController {

    private PaiementService paiementService;

    @PostMapping("/addPaiement")
    public PaiementDto addPaiement(@Valid @RequestBody PaiementDto paiementDto){
        return paiementService.createPaiement(paiementDto);
    }

    @GetMapping("/paiements")
    public List<PaiementDto> getAllPaiement(){
        return paiementService.getPaiements();
    }

    @GetMapping("/getPaiement/{id}")
    public PaiementDto getDeclaration(@PathVariable("id") int id){
        return paiementService.getPaiement(id);
    }
}
