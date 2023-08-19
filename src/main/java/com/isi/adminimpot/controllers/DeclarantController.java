package com.isi.adminimpot.controllers;

import com.isi.adminimpot.dto.DeclarantDto;
import com.isi.adminimpot.service.DeclarantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/declarants")
@AllArgsConstructor
public class DeclarantController {

    private DeclarantService declarantService;

    @PostMapping
    public DeclarantDto addDeclarant(@Valid @RequestBody DeclarantDto declarantDto){
        return declarantService.createDeclarant(declarantDto);
    }

    @GetMapping("/getAll")
    public List<DeclarantDto> getAllDeclarant(){
        return declarantService.getDeclarants();
    }

    @GetMapping("{id}")
    public DeclarantDto getDeclarant(@PathVariable("id") int id){
        return declarantService.getDeclarant(id);
    }
}
