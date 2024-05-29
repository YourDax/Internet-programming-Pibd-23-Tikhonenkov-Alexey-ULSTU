package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.ageratings.service.AgeRatingService;
import com.example.demo.core.error.NotFoundException;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.types.model.TypeEntity;
import com.example.demo.types.service.TypeService;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MovieServiceTests {
	@Autowired
	private TypeService typeService;

	@Autowired
	private AgeRatingService ageRatingService;

	@Autowired
	private MovieService movieService;

	@Test
	void getTest() {
		Assertions.assertThrows(NotFoundException.class, () -> movieService.get(0L));
	}

	@Test
    void testGetAll() {
		movieService
		.deleteAll();
		final var type1 = typeService.create(new TypeEntity(null, "Фильм"));
		final var type2 = typeService.create(new TypeEntity(null, "Сериал"));
		final var ageRating4 = ageRatingService.create(new AgeRatingEntity(null, "16+"));
		final var ageRating5 = ageRatingService.create(new AgeRatingEntity(null, "18+"));
        movieService.create(new MovieEntity(null, "Начало", type1,
				"src/assets/main-page/posters/Inception.jpg",
				"Кобб – талантливый вор, лучший в опасном искусстве извлечения: похищения ценных секретов из глубин подсознания во время сна...",
				2010, "США, Великобритания", "Кристофер Нолан", ageRating4,
				"https://www.youtube.com/embed/YoHD9XEInc0?si=AB3CDEfGHIJKLM"));
				
		movieService.create(new MovieEntity(null, "Матрица", type2,
				"src/assets/main-page/posters/TheMatrix.jpg",
				"Компьютерный хакер Томас Андерсон под псевдонимом Нео узнает шокирующую правду о своей реальности...",
				1999, "США, Австралия", "Лана и Лилли Вачовски", ageRating5,
				"https://www.youtube.com/embed/vKQi3bBA1y8?si=NO8PQRSTUVWX"));
		List<MovieEntity> result1 = movieService.getAll(0L, 0L);

		assertThat(result1).hasSize(2);
		assertEquals("Начало", result1.get(0).getTitle());
		assertEquals("Матрица", result1.get(1).getTitle());

		List<MovieEntity> result2 = movieService.getAll(1L, 0L);

		assertThat(result2).hasSize(1);
		assertEquals("Начало", result2.get(0).getTitle());	
		List<MovieEntity> result3 = movieService.getAll(0L, 1L);

		assertThat(result3).hasSize(1);
		assertEquals("Начало", result3.get(0).getTitle());	
		List<MovieEntity> result4 = movieService.getAll(1L, 1L);

		assertThat(result4).hasSize(1);
		assertEquals("Начало", result4.get(0).getTitle());		
        
    }

    
	@Test
	@Order(1)
	void createTest() {
		movieService
		.deleteAll();

		final var type1 = typeService.create(new TypeEntity(null, "Фильм"));
		final var type2 = typeService.create(new TypeEntity(null, "Сериал"));
		final var ageRating4 = ageRatingService.create(new AgeRatingEntity(null, "16+"));
		final var ageRating5 = ageRatingService.create(new AgeRatingEntity(null, "18+"));

		movieService.create(new MovieEntity(null, "Начало", type1,
				"src/assets/main-page/posters/Inception.jpg",
				"Кобб – талантливый вор, лучший в опасном искусстве извлечения: похищения ценных секретов из глубин подсознания во время сна...",
				2010, "США, Великобритания", "Кристофер Нолан", ageRating4,
				"https://www.youtube.com/embed/YoHD9XEInc0?si=AB3CDEfGHIJKLM"));
				
		movieService.create(new MovieEntity(null, "Матрица", type1,
				"src/assets/main-page/posters/TheMatrix.jpg",
				"Компьютерный хакер Томас Андерсон под псевдонимом Нео узнает шокирующую правду о своей реальности...",
				1999, "США, Австралия", "Лана и Лилли Вачовски", ageRating5,
				"https://www.youtube.com/embed/vKQi3bBA1y8?si=NO8PQRSTUVWX"));
				
		final MovieEntity last = movieService.create(new MovieEntity(null, "Во все тяжкие", type2,
				"src/assets/main-page/posters/BreakingBad.jpg",
				"Школьный учитель химии Уолтер Уайт получает диагноз 'рак' и решает заняться производством метамфетамина...",
				2008, "США", "Винс Гиллиган", ageRating5,
				"https://www.youtube.com/embed/HhesaQXLuRY?si=YZ123456789"));


		Assertions.assertEquals(3, movieService.getAll(0L, 0L).size());
		Assertions.assertEquals(1, movieService.getAll(1L, 1L).size());
		Assertions.assertEquals(last, movieService.get(3L));
	}

	@Test
	@Order(2)
	void updateTest() {
		final var type2 = typeService.create(new TypeEntity(null, "Сериал"));
		final var ageRating4 = ageRatingService.create(new AgeRatingEntity(null, "16+"));

		final String test = "TEST";
		final MovieEntity entity = movieService.get(3L);
		final String oldName = entity.getTitle();
		final MovieEntity newEntity = movieService.update(3L, new MovieEntity(null,
				test, type2,
				"src/assets/main-page/posters/PeakyBlinders.png",
				"Сериал рассказывает историю восхождения одной ирландской семьи...",
				2013, "Великобритания", "Энтони Бирн, Колм МакКарти", ageRating4,
				"https://www.youtube.com/embed/7cOfn_sjlXM?si=fxNoZjlUon1SZd70"));

		Assertions.assertEquals(3, movieService.getAll(0L , 0L).size());
		Assertions.assertEquals(newEntity, movieService.get(3L));
		Assertions.assertEquals(test, newEntity.getTitle());
		Assertions.assertNotEquals(oldName, newEntity.getTitle());
	}

	@Test
	@Order(3)
	void deleteTest() {
		final var type2 = typeService.create(new TypeEntity(null, "Сериал"));
		final var ageRating4 = ageRatingService.create(new AgeRatingEntity(null, "16+"));

		movieService.delete(3L);
		Assertions.assertEquals(2, movieService.getAll(0L, 0L).size());
		final MovieEntity last = movieService.get(2L);
		Assertions.assertEquals(2L, last.getId());

		final MovieEntity newEntity = movieService.create(new MovieEntity(null,
				"Острые козырьки", type2,
				"src/assets/main-page/posters/PeakyBlinders.png",
				"Сериал рассказывает историю восхождения одной ирландской семьи...",
				2013, "Великобритания", "Энтони Бирн, Колм МакКарти", ageRating4,
				"https://www.youtube.com/embed/7cOfn_sjlXM?si=fxNoZjlUon1SZd70"));
		Assertions.assertEquals(3, movieService.getAll(0L, 0L).size());
		Assertions.assertEquals(4L, newEntity.getId());
	}
}
