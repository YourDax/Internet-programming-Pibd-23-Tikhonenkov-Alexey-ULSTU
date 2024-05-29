package com.example.demo;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.ageratings.service.AgeRatingService;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.types.model.TypeEntity;
import com.example.demo.types.service.TypeService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	private final TypeService typeService;
	private final AgeRatingService ageratingService;
	private final MovieService movieService;

	public DemoApplication(TypeService typeService, AgeRatingService ageratingService, MovieService movieService) {
		this.typeService = typeService;
		this.ageratingService = ageratingService;
		this.movieService = movieService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0 && Objects.equals("--populate", args[0])) {
			log.info("Create default types values");
			final var type1 = typeService.create(new TypeEntity(null, "Фильм"));
			final var type2 = typeService.create(new TypeEntity(null, "Сериал"));

			log.info("Create default ageRatings values");
			final var agerating4 = ageratingService.create(new AgeRatingEntity(null, "16+"));
			final var agerating5 = ageratingService.create(new AgeRatingEntity(null, "18+"));

			log.info("Create default movies values");
			movieService.create(new MovieEntity(null, "Начало", type1,
					"src/assets/main-page/posters/Inception.jpg",
					"Кобб – талантливый вор, лучший в опасном искусстве извлечения: похищения ценных секретов из глубин подсознания во время сна...",
					2010, "США, Великобритания", "Кристофер Нолан", agerating4,
					"https://www.youtube.com/embed/YoHD9XEInc0?si=AB3CDEfGHIJKLM"));
					
			movieService.create(new MovieEntity(null, "Матрица", type1,
					"src/assets/main-page/posters/TheMatrix.jpg",
					"Компьютерный хакер Томас Андерсон под псевдонимом Нео узнает шокирующую правду о своей реальности...",
					1999, "США, Австралия", "Лана и Лилли Вачовски", agerating5,
					"https://www.youtube.com/embed/vKQi3bBA1y8?si=NO8PQRSTUVWX"));
					
			movieService.create(new MovieEntity(null, "Во все тяжкие", type2,
					"src/assets/main-page/posters/BreakingBad.jpg",
					"Школьный учитель химии Уолтер Уайт получает диагноз 'рак' и решает заняться производством метамфетамина...",
					2008, "США", "Винс Гиллиган", agerating5,
					"https://www.youtube.com/embed/HhesaQXLuRY?si=YZ123456789"));

		}
	}
}
