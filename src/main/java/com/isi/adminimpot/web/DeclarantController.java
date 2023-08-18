package com.isi.adminimpot.web;

import com.isi.adminimpot.dao.DeclarantDao;
import com.isi.adminimpot.entities.Declarant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class DeclarantController {
    private DeclarantDao declarantDao;

    @GetMapping(path = "/index")
    public String declarants(Model model){
        List<Declarant> declarants = declarantDao.findAll();
        model.addAttribute("listDeclarants", declarants);
        return "declarants";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        declarantDao.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
}
