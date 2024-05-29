package com.example.demo.users.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.core.api.PageAttributesMapper;
import com.example.demo.core.configurations.Constants;
import com.example.demo.core.security.UserPrincipal;
import com.example.demo.favorites.model.UserMovieEntity;
import com.example.demo.favorites.service.UserMovieService;
import com.example.demo.movies.api.MovieDTO;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.categories.api.CategorieDTO;
import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.users.model.UserMovieWithStatus;
//import com.example.demo.users.model.UserMovieWithStatus;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserProfileController {
    private static final String PROFILE_VIEW = "profile";

    private static final String PAGE_ATTRIBUTE = "page";
    private static final String TYPEID_ATTRIBUTE = "categorieId";
    private static final String PROFILE_ATTRIBUTE = "profile";

    private final MovieService movieService;
    private final CategorieService categorieService;
    private final UserService userService;
    private final UserMovieService userMovieService;
    private final ModelMapper modelMapper;

    public UserProfileController(
            MovieService movieService,
            CategorieService categorieService,
            UserService userService,
            UserMovieService userMovieService,
            ModelMapper modelMapper) {
        this.movieService = movieService;
        this.categorieService = categorieService;
        this.userService = userService;
        this.userMovieService = userMovieService;
        this.modelMapper = modelMapper;
    }

    private MovieDTO toDto(MovieEntity entity) {
        return modelMapper.map(entity, MovieDTO.class);
    }

    private CategorieDTO toCategorieDto(CategorieEntity entity) {
        return modelMapper.map(entity, CategorieDTO.class);
    }

    private UserMovieDto toMovieDto(UserMovieWithStatus entity) {
        return modelMapper.map(entity, UserMovieDto.class);
    }

    private UserMovieWithStatus toMovieWithStatus(UserMovieDto dto) {
        return modelMapper.map(dto, UserMovieWithStatus.class);
    }

    @GetMapping
    public String getProfile(
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            @RequestParam(name = TYPEID_ATTRIBUTE, defaultValue = "0") int categorieId,
            Model model,
            @AuthenticationPrincipal UserPrincipal principal) {
        final long userId = principal.getId();
        model.addAttribute(PAGE_ATTRIBUTE, page);
        model.addAttribute(TYPEID_ATTRIBUTE, categorieId);
        model.addAllAttributes(PageAttributesMapper.toAttributes(
                movieService.getAll(categorieId, page, Constants.DEFUALT_PAGE_SIZE),
                movie -> modelMapper.map(movie, MovieDTO.class)));
        model.addAttribute("categories",
                categorieService.getAll().stream()
                        .map(categorie -> modelMapper.map(categorie, CategorieDTO.class))
                        .toList());

        List<MovieDTO> favoriteMovies = userMovieService.getFavoriteMoviesByUserId(userId);
        model.addAttribute("favoriteMovies", favoriteMovies);

        return PROFILE_VIEW;
    }

    @PostMapping
    public String saveProfile(
            @ModelAttribute(name = PROFILE_ATTRIBUTE) @Valid UserProfileDto profile,
            BindingResult bindResult,
            Model model,
            @AuthenticationPrincipal UserPrincipal principal) {
        if (bindResult.hasErrors()) {
            return PROFILE_VIEW;
        }
        return Constants.REDIRECT_VIEW + "/";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = PAGE_ATTRIBUTE, defaultValue = "0") int page,
            @RequestParam(name = TYPEID_ATTRIBUTE, defaultValue = "0") int categorieId,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserPrincipal principal) {
        redirectAttributes.addAttribute(PAGE_ATTRIBUTE, page);
        redirectAttributes.addAttribute(TYPEID_ATTRIBUTE, categorieId);
        movieService.delete(id);
        return Constants.REDIRECT_VIEW + "/";
    }
}
