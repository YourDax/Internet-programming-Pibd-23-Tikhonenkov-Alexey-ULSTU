package com.example.demo.categories.api;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.configurations.Constants;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping(CategorieController.URL)

public class CategorieController {

    public static final String URL = Constants.ADMIN_PREFIX + "/categories";
    private static final String CATEGORIE_VIEW = "categories";
    private static final String CATEGORIE_ATTRIBUTE = "categories";
    private static final String CATEGORIE_EDIT_VIEW = "categories-edit";

    private final CategorieService categorieService;
    private final ModelMapper modelMapper;

    public CategorieController(CategorieService categorieService, ModelMapper modelMapper) {
        this.categorieService = categorieService;
        this.modelMapper = modelMapper;
    }

    private CategorieDTO toDto(CategorieEntity entity) {
        return modelMapper.map(entity, CategorieDTO.class);
    }

    private CategorieEntity toEntity(CategorieDTO categorieDTO) {
        return modelMapper.map(categorieDTO, CategorieEntity.class);
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute(
                "items",
                categorieService.getAll().stream()
                        .map(this::toDto)
                        .toList());
        return CATEGORIE_VIEW;
    }

    @GetMapping("/edit/")
    public String create(Model model) {
        model.addAttribute(CATEGORIE_ATTRIBUTE, new CategorieDTO());
        return CATEGORIE_EDIT_VIEW;
    }

    @PostMapping("/edit/")
    public String create(
            @ModelAttribute(name = CATEGORIE_ATTRIBUTE) @Valid CategorieDTO categorie,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return CATEGORIE_EDIT_VIEW;
        }
        categorieService.create(toEntity(categorie));
        return Constants.REDIRECT_VIEW + URL;
    }

    @GetMapping("/edit/{id}")
    public String update(
            @PathVariable(name = "id") Long id,
            Model model) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        model.addAttribute(CATEGORIE_ATTRIBUTE, toDto(categorieService.get(id)));
        return CATEGORIE_EDIT_VIEW;
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable(name = "id") Long id,
            @ModelAttribute(name = CATEGORIE_ATTRIBUTE) @Valid CategorieDTO categorie,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return CATEGORIE_EDIT_VIEW;
        }
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        categorieService.update(id, toEntity(categorie));
        return Constants.REDIRECT_VIEW + URL;
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable(name = "id") Long id) {
        categorieService.delete(id);
        return Constants.REDIRECT_VIEW + URL;
    }
}
