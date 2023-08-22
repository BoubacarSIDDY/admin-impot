package com.isi.adminimpot.controllers;

import com.isi.adminimpot.dto.DeclarantDto;
import com.isi.adminimpot.service.DeclarantService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/declarant")
@AllArgsConstructor @NoArgsConstructor
public class DeclarantController {

    private DeclarantService declarantService;

    @PostMapping("/newDeclarant")
    public DeclarantDto addDeclarant(@Valid @RequestBody DeclarantDto declarantDto){
        return declarantService.createDeclarant(declarantDto);
    }

    @GetMapping("/declarants")
    public List<DeclarantDto> getAllDeclarant(){
        return declarantService.getDeclarants();
    }

    @GetMapping("/getDeclarant/{id}")
    public DeclarantDto getDeclarant(@PathVariable("id") int id){
        return declarantService.getDeclarant(id);
    }
}
