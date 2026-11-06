package hr.academy.sightseeingapp.controller;

import hr.academy.sightseeingapp.dto.AttractionViewDto;
import hr.academy.sightseeingapp.service.AttractionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attractions-view")
public class AttractionViewController {
    private final AttractionService attractionService;

    @Autowired
    public AttractionViewController(AttractionService attractionService){
        this.attractionService = attractionService;
    }

    @GetMapping
    public String getAttractionsList(Model model){
        model.addAttribute("attractions", attractionService.getAttractions());
        return "attractions/list";
    }

    @GetMapping("/new")
    public String getNewAttractionList(Model model){
        model.addAttribute("attractionViewDto", new AttractionViewDto());
        model.addAttribute("types", attractionService.getTypes());
        return "attractions/form";
    }

    @PostMapping
    public String saveAttraction(@Valid @ModelAttribute("attractionViewDto") AttractionViewDto attractionViewDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("types", attractionService.getTypes());
            return "attractions/form";
        }
        attractionService.saveAttraction(attractionViewDto);
        return "redirect:/attractions-view";
    }
}
