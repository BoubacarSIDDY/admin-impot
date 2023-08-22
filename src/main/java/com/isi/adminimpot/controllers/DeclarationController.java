package com.isi.adminimpot.controllers;

import com.isi.adminimpot.dto.DeclarationDto;
import com.isi.adminimpot.service.DeclarationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/declaration")
@AllArgsConstructor @NoArgsConstructor
public class DeclarationController {

    private DeclarationService declarationService;


    @PostMapping("/newDeclaration")
    public DeclarationDto addDeclaration(@Valid @RequestBody DeclarationDto declarationDto){
        return declarationService.createDeclaration(declarationDto);
    }

    @GetMapping("/declarations")
    public List<DeclarationDto> getAllDeclaration(){
        return declarationService.getDeclarations();
    }

    @GetMapping("/getDeclaration/{id}")
    public DeclarationDto getDeclaration(@PathVariable("id") int id){
        return declarationService.getDeclaration(id);
    }
}
