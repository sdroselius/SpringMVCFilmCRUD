package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDaoImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT f.id, title, description, release_year, language_id, l.name AS language, "
				             + " rental_duration, rental_rate, length, replacement_cost, rating, special_features "
				        + " FROM film f JOIN language l ON f.language_id = l.id " 
					    + " WHERE f.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setLanguage(rs.getString("language"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				
				film.setActors(this.findActorsByFilmId(filmId));
				film.setCategories(this.findCategoriesByFilmId(film.getId()));
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error getting film " + filmId);
			sqle.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error getting actor " + actorId);
			sqle.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error getting film " + filmId);
			sqle.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> searchFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT f.id, title, description, release_year, language_id, l.name AS language, "
					    +      " rental_duration, rental_rate, length, replacement_cost, rating, special_features "
					    + " FROM film f JOIN language l ON f.language_id = l.id " 
					    +" WHERE title LIKE ? OR description LIKE ? ORDER BY title";
			keyword = "%" + keyword + "%";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
//			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setLanguage(rs.getString("language"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				
				film.setActors(this.findActorsByFilmId(film.getId()));
				film.setCategories(this.findCategoriesByFilmId(film.getId()));
				films.add(film);
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error getting films by keyword [" + keyword + "]");
			sqle.printStackTrace();
		}
		return films;
	}

	@Override
	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, " 
			                            + " rental_rate, length, replacement_cost, rating, special_features) "
			          + " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId()); 
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			
			int updateCount = stmt.executeUpdate();
			System.out.println("********** " + updateCount);
			if ( updateCount == 1 ) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if ( generatedKeys.next() ) {
					film.setId(generatedKeys.getInt(1));
				}
				conn.commit();
			}
			else {
				film = null;
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			film = null;
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return film;
	}

	@Override
	public Film updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, " 
			                            + " rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? "
					     +" WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId()); 
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());
			
			int updateCount = stmt.executeUpdate();
			if ( updateCount == 1 ) {
				conn.commit();
			}
			else {
				conn.rollback();
				film = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			film = null;
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return film;
	}

	@Override
	public boolean deleteFilm(int filmIdToDelete) {
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmIdToDelete);
			int updateCount = stmt.executeUpdate();
			return updateCount == 1;
		}
		catch (SQLException sqle) {
			System.err.println("Error deleting film " + filmIdToDelete);
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Film> randomFilms(int howMany) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT f.id, title, description, release_year, language_id, l.name AS language, "
					    +      " rental_duration, rental_rate, length, replacement_cost, rating, special_features "
					    + " FROM film f JOIN language l ON f.language_id = l.id " 
					    +" ORDER BY RAND() LIMIT ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, howMany);
//			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setLanguage(rs.getString("language"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				
				film.setActors(this.findActorsByFilmId(film.getId()));
				film.setCategories(this.findCategoriesByFilmId(film.getId()));
				films.add(film);
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error getting films");
			sqle.printStackTrace();
		}
		return films;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.err.println("Unable to load database driver:");
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> findCategoriesByFilmId(int filmId) {
		List<Category> categories = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, name FROM category JOIN film_category ON category.id = film_category.category_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categories.add(category);
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error getting categories " + filmId);
			sqle.printStackTrace();
		}
		return categories;
	}
}

