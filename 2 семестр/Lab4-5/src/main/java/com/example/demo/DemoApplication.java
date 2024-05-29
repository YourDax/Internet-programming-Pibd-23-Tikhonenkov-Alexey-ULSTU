package com.example.demo;

import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.configurations.Constants;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.model.UserRole;
import com.example.demo.users.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final Logger _logger = LoggerFactory.getLogger(DemoApplication.class);
    private final UserService userService;
    private final CategorieService categorieService;
    private final MovieService movieService;

    public DemoApplication(UserService userService, CategorieService categorieService, MovieService movieService) {
        this.userService = userService;
        this.categorieService = categorieService;
        this.movieService = movieService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final var admin = new UserEntity("admin", "admin");

        final var u1 = new UserEntity("alexey", "alexey");
        final var user1 = userService.create(new UserEntity("user1", Constants.DEFAULT_PASSWORD));

        IntStream.range(2, 27)
                .forEach(value -> userService.create(
                        new UserEntity("user".concat(String.valueOf(value)), Constants.DEFAULT_PASSWORD)));

        final var cat1 = new CategorieEntity("Драма", null);
        final var cat2 = new CategorieEntity("Комедия", null);
        final var cat3 = new CategorieEntity("Триллер", null);
        final var cat4 = new CategorieEntity("Ужасы", null);
        final var cat5 = new CategorieEntity("Фантастика", null);
        final var cat6 = new CategorieEntity("Мелодрама", null);
        final var cat7 = new CategorieEntity("Боевик", null);
        final var cat8 = new CategorieEntity("Анимация", null);
        final var cat9 = new CategorieEntity("Детектив", null);
        final var cat10 = new CategorieEntity("Документальный", null);

        admin.setRole(UserRole.ADMIN);

        u1.setRole(UserRole.USER);

        userService.create(admin);

        userService.create(u1);

        categorieService.create(cat1);
        categorieService.create(cat2);
        categorieService.create(cat3);
        categorieService.create(cat4);
        categorieService.create(cat5);
        categorieService.create(cat6);
        categorieService.create(cat7);
        categorieService.create(cat8);
        categorieService.create(cat9);
        categorieService.create(cat10);

        movieService.create(new MovieEntity(cat1, "Зелёная миля",
                "Тюремный надзиратель становится свидетелем чудесных событий после прибытия нового заключённого.",
                "189", "1"));
        movieService.create(new MovieEntity(cat2, "Мальчишник в Вегасе",
                "Группа друзей пытается восстановить события минувшей ночи после безумной вечеринки в Лас-Вегасе.",
                "100", "1"));
        movieService.create(new MovieEntity(cat3, "Исчезнувшая",
                "Мужчина становится главным подозреваемым в исчезновении своей жены.", "149", "1"));
        movieService.create(new MovieEntity(cat4, "Заклятие",
                "Семья сталкивается с зловещим присутствием в их новом доме.", "112", "1"));
        movieService.create(new MovieEntity(cat5, "Интерстеллар",
                "Группа исследователей отправляется через червоточину в поисках нового дома для человечества.", "169",
                "1"));
        movieService.create(new MovieEntity(cat6, "Титаник",
                "История любви между Джеком и Роуз на борту злополучного Титаника.", "195", "1"));
        movieService.create(new MovieEntity(cat7, "Матрица",
                "Программист обнаруживает, что мир вокруг него является искусственной реальностью.", "136", "1"));
        movieService.create(new MovieEntity(cat8, "Король Лев",
                "Молодой лев Симба учится быть королём после трагической смерти отца.", "88", "1"));
        movieService.create(new MovieEntity(cat9, "Шерлок Холмс",
                "Известный детектив расследует серию загадочных преступлений в Лондоне.", "128", "1"));
        movieService.create(new MovieEntity(cat10, "Круги на полях",
                "Документальный фильм о загадочных кругах на полях и их возможном происхождении.", "94", "1"));

        _logger.info("Admin user added");
    }

}
