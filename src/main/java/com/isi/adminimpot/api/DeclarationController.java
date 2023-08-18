package com.isi.adminimpot.api;

import com.isi.adminimpot.entities.Declaration;
import com.isi.adminimpot.repositories.DeclarationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declarations")
@AllArgsConstructor
public class DeclarationController {

    private DeclarationRepository declarationRepository;

    @PostMapping
    public Declaration addDeclaration(@RequestBody Declaration declaration){
        return declarationRepository.save(declaration);
    }

    @GetMapping
    public List<Declaration> getAllDeclaration(){
        return declarationRepository.findAll();
    }
}
