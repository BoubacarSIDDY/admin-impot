package com.isi.adminimpot.controllers;

import com.isi.adminimpot.dto.DeclarationDto;
import com.isi.adminimpot.entities.Declaration;
import com.isi.adminimpot.repositories.IDeclarationRepository;
import com.isi.adminimpot.service.DeclarationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/declarations")
@AllArgsConstructor
public class DeclarationController {

    private IDeclarationRepository iDeclarationRepository;
    private DeclarationService declarationService;

    @PostMapping
    public Declaration addDeclaration(@Valid @RequestBody Declaration declaration){
        return iDeclarationRepository.save(declaration);
    }

    @GetMapping("/getAll")
    public List<DeclarationDto> getAllDeclaration(){
        return declarationService.getDeclarations();
    }

    @GetMapping("{id}")
    public DeclarationDto getDeclaration(@PathVariable("id") int id){
        return declarationService.getDeclaration(id);
    }
}
