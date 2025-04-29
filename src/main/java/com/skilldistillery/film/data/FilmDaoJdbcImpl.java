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
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDaoJdbcImpl implements FilmDAO {
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
						+ "WHERE f.id = ?";
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
			}
		} catch (SQLException sqle) {
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
		} catch (SQLException sqle) {
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
			if (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actors.add(actor);
			}
		} catch (SQLException sqle) {
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
					+ " rental_duration, rental_rate, length, replacement_cost, rating, special_features "
					+ " FROM film f JOIN language l ON f.language_id = l.id "
					+ " WHERE title LIKE ? OR description LIKE ?";
			keyword = "%" + keyword + "%";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);
			System.out.println(stmt);
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
				films.add(film);
			}
		} catch (SQLException sqle) {
			System.err.println("Error getting films by keyword [" + keyword + "]");
			sqle.printStackTrace();
		}
		return films;
	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load database driver:");
			e.printStackTrace();
			System.err.println("Exiting.");
			System.exit(1); // No point in continuing.
		}
	}

	@Override
	public Film addFilm(Film newFilm) {
		if (newFilm.getLanguageId() == 0) {
			newFilm.setLanguageId(1);
		}
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_rate, rental_duration "
					+ ", replacement_cost, length, rating, special_features) " + " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newFilm.getTitle());
			stmt.setString(2, newFilm.getDescription());
			stmt.setInt(3, newFilm.getReleaseYear());
			stmt.setInt(4, newFilm.getLanguageId());
			stmt.setDouble(5, newFilm.getRentalRate());
			stmt.setInt(6, newFilm.getRentalDuration());
			stmt.setDouble(7, newFilm.getReplacementCost());
			stmt.setInt(8, newFilm.getLength());
			stmt.setString(9, newFilm.getRating());
			stmt.setString(10, newFilm.getSpecialFeatures());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int filmId = keys.getInt(1);
					newFilm.setId(filmId);
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return newFilm;
	}

	@Override
	public Film updateFilm(Film updatingFilm) {
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=? "
					+ ", rental_rate=?, rental_duration=?, replacement_cost=?, length=?, rating=? "
					+ ", special_features=? WHERE id=?"
					;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, updatingFilm.getTitle());
			stmt.setString(2, updatingFilm.getDescription());
			stmt.setInt(3, updatingFilm.getReleaseYear());
			stmt.setInt(4, updatingFilm.getLanguageId());
			stmt.setDouble(5, updatingFilm.getRentalRate());
			stmt.setInt(6, updatingFilm.getRentalDuration());
			stmt.setDouble(7, updatingFilm.getReplacementCost());
			stmt.setInt(8, updatingFilm.getLength());
			stmt.setString(9, updatingFilm.getRating());
			stmt.setString(10, updatingFilm.getSpecialFeatures());
			stmt.setInt(11, updatingFilm.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}
			return updatingFilm;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteFilm(int filmId) {
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
