package com.isi.adminimpot.api;

import com.isi.adminimpot.repositories.DeclarantRepository;
import com.isi.adminimpot.entities.Declarant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declarants")
@AllArgsConstructor
public class DeclarantController {

    private DeclarantRepository declarantRepository;

    @PostMapping
    public Declarant addDeclarant(@RequestBody Declarant declarant){
        return declarantRepository.save(declarant);
    }

    @GetMapping
    public List<Declarant> getAllDeclarant(){
        return declarantRepository.findAll();
    }
}
