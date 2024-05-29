package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final CategorieService categorieService;
	private final MovieService movieService;

	public DemoApplication(CategorieService categorieService, MovieService movieService) {
		this.categorieService = categorieService;
		this.movieService = movieService;

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (args.length > 0 && Objects.equals("--populate", args[0])) {
			List<CategorieEntity> categories = new ArrayList<>();

			final var categorie1 = categorieService.create(new CategorieEntity(
					"Драма",  "placeholder"));
			final var categorie2 = categorieService.create(new CategorieEntity(
					"Комедия", "placeholder"));
			final var categorie3 = categorieService.create(new CategorieEntity(
					"Хоррор", "placeholder"));

			categories.add(categorie1);
			categories.add(categorie2);
			movieService.create(new MovieEntity(categories, "Астрал", "1", "1", "1"));
			categories.clear();
			categories.add(categorie3);
			categories.add(categorie1);
			movieService.create(new MovieEntity(categories, "Хатико", "2", "2", "2"));
			categories.clear();
			categories.add(categorie2);
			categories.add(categorie1);
			movieService.create(new MovieEntity(categories, "Паранормальное", "3", "3", "3"));
			categories.clear();
			categories.add(categorie3);
			categories.add(categorie2);
			movieService.create(new MovieEntity(categories, "Побег из Шоушенка", "4", "4","4"));
			categories.clear();
			categories.add(categorie1);
			categories.add(categorie2);
			movieService.create(new MovieEntity(categories, "Бойцовский клуб", "5", "5", "5"));

		}
	}

}
