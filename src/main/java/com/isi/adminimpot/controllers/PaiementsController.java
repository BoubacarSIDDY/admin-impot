package com.isi.adminimpot.controllers;

import com.isi.adminimpot.dto.PaiementDto;
import com.isi.adminimpot.service.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API REST (Paiements)
 */
@RestController
@RequestMapping("/api/paiements")
@AllArgsConstructor
public class PaiementsController {

    private PaiementService paiementService;

    @PostMapping
    public PaiementDto addPaiement(@Valid @RequestBody PaiementDto paiementDto){
        return paiementService.createPaiement(paiementDto);
    }

    @GetMapping("/getAll")
    public List<PaiementDto> getAllPaiement(){
        return paiementService.getPaiements();
    }

    @GetMapping("{id}")
    public PaiementDto getDeclaration(@PathVariable("id") int id){
        return paiementService.getPaiement(id);
    }
}
