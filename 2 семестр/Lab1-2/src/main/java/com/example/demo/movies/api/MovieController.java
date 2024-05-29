package com.example.demo.movies.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.configuration.Constants;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.types.service.TypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/movie")
public class MovieController {
    private final MovieService itemService;
    private final TypeService typeService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService itemService, TypeService typeService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.typeService = typeService;
        this.modelMapper = modelMapper;
    }

    private MovieDto toDto(MovieEntity entity) {
        return modelMapper.map(entity, MovieDto.class);
    }

    private MovieEntity toEntity(MovieDto dto) {
        final MovieEntity entity = modelMapper.map(dto, MovieEntity.class);
        entity.setType(typeService.get(dto.getTypeId()));
        return entity;
    }

    @GetMapping
    public List<MovieDto> getAll(@RequestParam(name = "typeId", defaultValue = "0") Long typeId, @RequestParam(name = "typeId", defaultValue = "0") Long ageratingId) {
        return itemService.getAll(typeId , ageratingId).stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public MovieDto get(@PathVariable(name = "id") Long id) {
        return toDto(itemService.get(id));
    }

    @PostMapping
    public MovieDto create(@RequestBody @Valid MovieDto dto) {
        return toDto(itemService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public MovieDto update(@PathVariable(name = "id") Long id, @RequestBody MovieDto dto) {
        return toDto(itemService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public MovieDto delete(@PathVariable(name = "id") Long id) {
        return toDto(itemService.delete(id));
    }
}
