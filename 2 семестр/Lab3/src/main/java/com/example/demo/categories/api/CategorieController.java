package com.example.demo.categories.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.configurations.Constants;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(Constants.API_URL + "/categorie")

public class CategorieController {

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
    public List<CategorieDTO> getAll() {
        return categorieService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public CategorieDTO get(@PathVariable(name = "id") Integer id) {
        return toDto(categorieService.get(id));
    }

    @PostMapping
    public CategorieDTO create(@RequestBody @Valid CategorieDTO categorieDTO) {

        return toDto(categorieService.create(toEntity(categorieDTO)));
    }

    @PutMapping("/{id}")
    public CategorieDTO update(@PathVariable(name = "id") Integer id, @RequestBody CategorieDTO categorieDTO) {
        return toDto(categorieService.update(id, toEntity(categorieDTO)));
    }

    @DeleteMapping("/{id}")
    public CategorieDTO delete(@PathVariable(name = "id") Integer id) {
        return toDto(categorieService.delete(id));
    }

}
