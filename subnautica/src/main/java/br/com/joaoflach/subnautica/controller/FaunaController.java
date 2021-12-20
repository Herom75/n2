package br.com.joaoflach.subnautica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.joaoflach.subnautica.models.dto.FaunaDTO;



@Controller
@RequestMapping("/fauna")
public class FaunaController {
    @Autowired
    @GetMapping
    public String formInsert(Model model){
        FaunaDTO fauna = new FaunaDTO();
        model.addAttribute("objFauna", fauna);
        return "pages/home";
    }

    @PostMapping
    public String insert(@ModelAttribute FaunaDTO bookDTO) {
        var Fauna = FaunaService.insertOrUpdate(
                FaunaMapping.toFauna(bookDTO));
        if (Fauna.getId() != 0) {
            return "pages/success";
        } else {
            return "pages/error";
        }
    }
}

