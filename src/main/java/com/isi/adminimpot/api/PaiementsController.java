package com.isi.adminimpot.api;

import com.isi.adminimpot.entities.Paiement;
import com.isi.adminimpot.repositories.PaiementRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST (Paiements)
 */
@RestController
@RequestMapping("/api/paiements")
@AllArgsConstructor
public class PaiementsController {

    private PaiementRepository paiementRepository;

    @PostMapping
    public Paiement addPaiement(@RequestBody Paiement paiement){
        return paiementRepository.save(paiement);
    }

    @GetMapping
    public List<Paiement> getAllPaiement(){
        return paiementRepository.findAll();
    }
}
