package com.example.demo.movies.api;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.configurations.Constants;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(Constants.API_URL + "/movie")
public class MovieController {

    private final MovieService movieService;
    private final CategorieService categorieService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService movieService, CategorieService categorieService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.categorieService = categorieService;
        this.movieService = movieService;
    }

    private MovieDTO toDto(MovieEntity entity) {
        return modelMapper.map(entity, MovieDTO.class);
    }

    private MovieEntity toEntity(MovieDTO dto) {
        final MovieEntity entity = modelMapper.map(dto, MovieEntity.class);
        List<CategorieEntity> categories = new ArrayList<>();
        categories.addAll(categorieService.getAllCategories(dto.getCategoriesIdList()));
        entity.setCategories(categories);
        return entity;
    }

    @GetMapping
    public List<MovieDTO> getAll(@RequestParam(name = "categorieId", defaultValue = "0") Integer categorieId) {
        return movieService.getAll(categorieId).stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public MovieDTO get(@PathVariable(name = "id") Integer id) {
        return toDto(movieService.get(id));
    }

    @PostMapping
    public MovieDTO create(@RequestBody @Valid MovieDTO dto) {
        return toDto(movieService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public MovieDTO update(@PathVariable(name = "id") Integer id, @RequestBody MovieDTO dto) {
        return toDto(movieService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public MovieDTO delete(@PathVariable(name = "id") Integer id) {
        return toDto(movieService.delete(id));
    }

}
