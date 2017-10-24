package filmDatabaseApp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * Film represents a Film record that will be used in
 * the GUI. This class also declares rules for displaying 
 * the data, as to be read in by IO methods.
 * 
 * @author Nigel Mckenzie-Ryan
 * @version 1.0
 */

public class Film implements Serializable {
	private String filmName;
	private int filmRating;
	private int releaseDate;
	private Genre filmGenre;
	private Cast filmCast;

	// CREATING CAST OBJECT
	public class Cast implements Serializable {
		private ArrayList<String> castList;

		public Cast(ArrayList<String> castList) {
			this.castList = castList;
		}

		public Cast() {
			this.castList = new ArrayList<String>();
		}

		@Override
		public String toString() {
			String castL = "";
			for (String actor : castList) {
				castL += actor + ";";
			}
			return castL;
		}

	}

	// CONSTRUTORS
	public Film(String name, int rate, int relDate, Genre genre) {
		super();
		this.filmName = name;
		this.filmRating = rate;
		this.releaseDate = relDate;
		this.filmGenre = genre;
		this.filmCast = new Cast();
	}

	public Film() {
		super();
		this.filmName = "Not Set";
		this.filmRating = 0;
		this.releaseDate = 1500;
		this.filmGenre = Genre.NOGENRE;
		this.filmCast = new Cast();
	}

	// GET + SET
	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public int getFilmRating() {
		return filmRating;
	}

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Genre getFilmGenre() {
		return filmGenre;
	}

	public void setFilmGenre(Genre filmGenre) {
		this.filmGenre = filmGenre;
	}

	public Cast getFilmCast() {
		return filmCast;
	}

	public ArrayList<String> getFilmCastList() {
		return filmCast.castList;
	}

	//Adds a single cast member to a Film's Cast.
	public void addCast(String actor) {
		this.filmCast.castList.add(actor);
	}

	// Dynamically sets a group of cast members to a Film
	public void setFilmCast(String... actors) {
		Film demoFilm = new Film();
		for (int i = 0; i < actors.length; i++) {
			demoFilm.addCast(actors[i]);
		}

		this.filmCast = demoFilm.filmCast;
	}

	// Presents the film in a more presentable way for the user.
	public String scanFilm() {
		return "Film: " + filmName + " | Rating: " + filmRating + "/5 Stars | Released: " + releaseDate + " | Genre: "
				+ filmGenre.getGValues() + " | Cast: " + filmCast + " | ";
	}

	//Defines the format for the Film object. ";" is used as a Demlimiter.
	@Override
	public String toString() {
		return filmName + ";" + filmRating + ";" + releaseDate + ";" + filmGenre.getGValues() + ";" + filmCast + ";";
	}
}
