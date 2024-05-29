package com.example.demo.movies.api;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.categories.api.CategorieDTO;
import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.api.PageAttributesMapper;
import com.example.demo.core.configurations.Constants;
import com.example.demo.core.security.UserPrincipal;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(MovieController.URL)
public class MovieController {

    public static final String URL = Constants.ADMIN_PREFIX + "/movies";
    private static final String MOVIE_VIEW = "movies";
    private static final String MOVIE_ATTRIBUTE = "movies";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String MOVIE_EDIT_VIEW = "movies-edit";

    private final UserService userService;
    private final MovieService movieService;
    private final CategorieService categorieService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService movieService, CategorieService categorieService,
            ModelMapper modelMapper,
            UserService userService) {
        this.userService = userService;
        this.movieService = movieService;
        this.categorieService = categorieService;
        this.modelMapper = modelMapper;

    }

    private MovieDTO toDto(MovieEntity entity) {
        return modelMapper.map(entity, MovieDTO.class);
    }

    private MovieEntity toEntity(MovieDTO dto) {
        final MovieEntity entity = modelMapper.map(dto, MovieEntity.class);
        entity.setCategorie(categorieService.get(dto.getCategorieId()));
        return entity;
    }

    private CategorieDTO toDto(CategorieEntity entity) {
        return modelMapper.map(entity, CategorieDTO.class);
    }

    @GetMapping
    public String getAll(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            Model model) {
        final Map<String, Object> attributes = PageAttributesMapper.toAttributes(
                movieService.getAll(0L, page, Constants.DEFUALT_PAGE_SIZE), this::toDto);
        model.addAllAttributes(attributes);
        model.addAttribute(PAGE_ATTRIBUTE, page);
        return MOVIE_VIEW;
    }

    @GetMapping("/edit/")
    public String create(Model model) {
        model.addAttribute(MOVIE_ATTRIBUTE, new MovieDTO());
        model.addAttribute("categories", categorieService.getAll().stream().map(this::toDto).toList());
        return MOVIE_EDIT_VIEW;
    }

    @PostMapping("/edit/")
    public String create(
            @ModelAttribute(name = MOVIE_ATTRIBUTE) @Valid MovieDTO movies,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categorieService.getAll().stream().map(this::toDto).toList());
            return MOVIE_EDIT_VIEW;
        }
        movieService.create(toEntity(movies));
        return Constants.REDIRECT_VIEW + URL;
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        model.addAttribute(MOVIE_ATTRIBUTE, toDto(movieService.get(id)));
        model.addAttribute("categories", categorieService.getAll().stream().map(this::toDto).toList());
        return MOVIE_EDIT_VIEW;
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable(name = "id") Long id,
            @ModelAttribute(name = MOVIE_ATTRIBUTE) @Valid MovieDTO movies,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categorieService.getAll().stream().map(this::toDto).toList());
            return MOVIE_EDIT_VIEW;
        }
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        movieService.update(id, toEntity(movies));
        return Constants.REDIRECT_VIEW + URL;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        movieService.delete(id);
        return Constants.REDIRECT_VIEW + URL;
    }

}
