package com.example.demo.ageratings.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.ageratings.service.AgeRatingService;
import com.example.demo.core.configuration.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/ageRating")
public class AgeRatingController {
    private final AgeRatingService ageRatingService;
    private final ModelMapper modelMapper;

    public AgeRatingController(AgeRatingService ageRatingService, ModelMapper modelMapper) {
        this.ageRatingService = ageRatingService;
        this.modelMapper = modelMapper;
    }

    private AgeRatingDto toDto(AgeRatingEntity entity) {
        return modelMapper.map(entity, AgeRatingDto.class);
    }

    private AgeRatingEntity toEntity(AgeRatingDto dto) {
        return modelMapper.map(dto, AgeRatingEntity.class);
    }

    @GetMapping
    public List<AgeRatingDto> getAll() {
        return ageRatingService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public AgeRatingDto get(@PathVariable(name = "id") Long id) {
        return toDto(ageRatingService.get(id));
    }

    @PostMapping
    public AgeRatingDto create(@RequestBody @Valid AgeRatingDto dto) {
        return toDto(ageRatingService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public AgeRatingDto update(@PathVariable(name = "id") Long id, @RequestBody AgeRatingDto dto) {
        return toDto(ageRatingService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public AgeRatingDto delete(@PathVariable(name = "id") Long id) {
        return toDto(ageRatingService.delete(id));
    }
}
