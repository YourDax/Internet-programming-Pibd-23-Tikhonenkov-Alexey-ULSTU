package com.example.demo.favorites.api;

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
import com.example.demo.favorites.model.FavoriteEntity;
import com.example.demo.favorites.service.FavoriteService;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public FavoriteController(FavoriteService favoriteService, UserService userService,
            MovieService movieService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.movieService = movieService;
    }

    private FavoriteDto toDto(FavoriteEntity entity) {
        return modelMapper.map(entity, FavoriteDto.class);
    }

    private FavoriteEntity toEntity(FavoriteDto dto) {
        final FavoriteEntity entity = modelMapper.map(dto, FavoriteEntity.class);
        entity.setMovie(movieService.get(dto.getMovieId()));
        entity.setUser(userService.get(dto.getUserId()));
        return entity;
    }

    @GetMapping
    public List<FavoriteDto> getAll(@RequestParam(name = "userId", defaultValue = "0") Integer userId) {
        return favoriteService.getAll(userId).stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public FavoriteDto get(@PathVariable(name = "id") Integer id) {
        return toDto(favoriteService.get(id));
    }

    @PostMapping
    public FavoriteDto create(@RequestBody @Valid FavoriteDto dto) {
        return toDto(favoriteService.create(toEntity(dto)));
    }

    @PutMapping("/{id}")
    public FavoriteDto update(@PathVariable(name = "id") Integer id,
            @RequestBody FavoriteDto dto) {
        return toDto(favoriteService.update(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public FavoriteDto delete(@PathVariable(name = "id") Integer id) {
        return toDto(favoriteService.delete(id));
    }
}
