package com.example.demo.favorites.api;

import com.example.demo.favorites.api.UserMovieDTO;
import com.example.demo.favorites.model.UserMovieEntity;
import com.example.demo.favorites.service.UserMovieService;
import com.example.demo.movies.api.MovieDTO;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;
import com.example.demo.core.security.UserPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user-movies")
public class UserMovieController {

    private static final Logger logger = LoggerFactory.getLogger(UserMovieController.class);
    private final UserMovieService userMovieService;
    private final MovieService movieService;
    private final UserService userService;

    public UserMovieController(UserMovieService userMovieService, UserService userService, MovieService movieService) {
        this.userMovieService = userMovieService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @PostMapping("/add-to-favorites")
    public String addToFavorites(@RequestParam Long movieId,
            @AuthenticationPrincipal UserPrincipal userPrincipal, RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Adding movie to favorites: movieId={}, userId={}", movieId, userPrincipal.getId());
            UserMovieEntity userMovieEntity = new UserMovieEntity();
            userMovieEntity.setUser(userService.get(userPrincipal.getId()));
            userMovieEntity.setMovie(
                    userMovieService.getMovieById(movieId).orElseThrow(() -> new RuntimeException("Movie not found")));
            userMovieService.createUserMovie(userMovieEntity);
            return "redirect:/";
        } catch (Exception e) {
            logger.error("Error adding movie to favorites", e);
            redirectAttributes.addFlashAttribute("error",
                    "Произошла ошибка при добавлении фильма в избранное.");
            return "redirect:/";
        }

    }

    @PostMapping("/remove-from-favorites")
    public String removeFromFavorites(@RequestParam Long movieId, @AuthenticationPrincipal UserPrincipal userPrincipal,
            RedirectAttributes redirectAttributes) {
        try {
            logger.debug("Removing movie from favorites: movieId={}, userId={}", movieId, userPrincipal.getId());
            userMovieService.removeUserMovie(userPrincipal.getId(), movieId);
            return "redirect:/";
        } catch (Exception e) {
            logger.error("Error removing movie from favorites", e);
            redirectAttributes.addFlashAttribute("error", "Произошла ошибка при удалении фильма из избранного.");
            return "redirect:/";
        }
    }

    @GetMapping("/favorites")
    public String getFavoriteMovies(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        if (userPrincipal == null) {
            return "redirect:/login";
        }

        List<MovieDTO> favoriteMovies = userMovieService.getFavoriteMoviesByUserId(userPrincipal.getId());
        model.addAttribute("favoriteMovies", favoriteMovies);
        return "favorites";
    }

    @GetMapping
    public List<UserMovieDTO> getAllUserMovies() {
        return userMovieService.getAllUserMovies().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMovieDTO> getUserMovieById(@PathVariable Long id) {
        Optional<UserMovieEntity> userMovieEntity = userMovieService.getUserMovieById(id);
        return userMovieEntity.map(userMovie -> ResponseEntity.ok(convertToDto(userMovie)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserMovieDTO> getUserMoviesByUserId(@PathVariable Long userId) {
        return userMovieService.getUserMoviesByUserId(userId).stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/{movieId}")
    public List<UserMovieDTO> getUserMoviesByMovieId(@PathVariable Long movieId) {
        return userMovieService.getUserMoviesByMovieId(movieId).stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public UserMovieDTO createUserMovie(@RequestBody UserMovieDTO userMovieDTO) {
        UserMovieEntity userMovieEntity = convertToEntity(userMovieDTO);
        UserMovieEntity createdUserMovie = userMovieService.createUserMovie(userMovieEntity);
        return convertToDto(createdUserMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMovieDTO> updateUserMovie(@PathVariable Long id, @RequestBody UserMovieDTO userMovieDTO) {
        UserMovieEntity userMovieEntity = convertToEntity(userMovieDTO);
        UserMovieEntity updatedUserMovie = userMovieService.updateUserMovie(id, userMovieEntity);
        return ResponseEntity.ok(convertToDto(updatedUserMovie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserMovie(@PathVariable Long id) {
        userMovieService.deleteUserMovie(id);
        return ResponseEntity.noContent().build();
    }

    private UserMovieDTO convertToDto(UserMovieEntity userMovieEntity) {
        UserMovieDTO userMovieDTO = new UserMovieDTO();
        userMovieDTO.setId(userMovieEntity.getId());
        userMovieDTO.setUserId(userMovieEntity.getUser().getId());
        userMovieDTO.setMovieId(userMovieEntity.getMovie().getId());
        return userMovieDTO;
    }

    private UserMovieEntity convertToEntity(UserMovieDTO userMovieDTO) {
        UserMovieEntity userMovieEntity = new UserMovieEntity();
        userMovieEntity.setId(userMovieDTO.getId());
        // Assuming you have a method to fetch user and movie by their IDs
        userMovieEntity.setUser(userService.get(userMovieDTO.getUserId()));
        userMovieEntity.setMovie(movieService.get(userMovieDTO.getMovieId()));
        return userMovieEntity;
    }
}
