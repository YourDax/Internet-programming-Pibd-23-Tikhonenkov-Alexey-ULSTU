package com.example.demo.viewed.api;

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

import com.example.demo.core.configurations.Constants;
import com.example.demo.viewed.model.ViewedEntity;
import com.example.demo.viewed.service.ViewedService;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/viewed")
public class ViewedController {

    private final ViewedService viewedService;
    private final UserService userService;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public ViewedController(
            ViewedService viewedService, UserService userService,
            MovieService movieService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.viewedService = viewedService;
        this.movieService = movieService;
    }

    private ViewedDto toDto(ViewedEntity entity) {
        return modelMapper.map(entity, ViewedDto.class);
    }

    private ViewedEntity toEntity(ViewedDto dto) {
        final ViewedEntity entity = modelMapper.map(dto, ViewedEntity.class);
        entity.setMovie(movieService.get(dto.getMovieId()));
        entity.setUser(userService.get(dto.getUserId()));
        return entity;
    }

    @GetMapping
    public List<ViewedDto> getAll(@RequestParam(name = "userId", defaultValue = "0") Integer userId) {
        return viewedService.getAll(userId).stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public ViewedDto get(@PathVariable(name = "id") Integer id) {
        return toDto(viewedService.get(id));
    }

    @PostMapping
    public ViewedDto create(@RequestBody @Valid ViewedDto dto) {
        return toDto(viewedService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public ViewedDto update(@PathVariable(name = "id") Integer id,
            @RequestBody ViewedDto dto) {
        return toDto(viewedService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ViewedDto delete(@PathVariable(name = "id") Integer id) {
        return toDto(viewedService.delete(id));
    }
}
