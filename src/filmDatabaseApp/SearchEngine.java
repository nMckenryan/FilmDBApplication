package filmDatabaseApp;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * SearchEngine defines the search algorithms used by 
 * FilmDatabase, then returns a FilmDatabase to be displayed
 * in the GUI/View..
 * 
 * @author Nigel Mckenzie-Ryan
 * @version 1.0
 */

public class SearchEngine implements Serializable{

	private FilmDatabase filmDB;

	/** 
	 * Main Search Algorithm,
	 * @param String from Search Field in View
	 * @return a FilmDatabase filtered by the parameter String  
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */ 
	public FilmDatabase search(String inputCriteria) {
		FilmDatabase database = new FilmDatabase();
		inputCriteria.toLowerCase(); // Formatting inputed Data to conform to the format of the Film Object.

		String[] splitCriteria = inputCriteria.split("\\s+"); 
		for (int letterCount = 0; letterCount < splitCriteria.length; letterCount++) {
			splitCriteria[letterCount] = splitCriteria[letterCount].replaceAll("[^\\w]", "");
		}

		if(inputCriteria.isEmpty()) { //Alerts User if Criteria is empty.
			System.out.println("Please enter Search Criteria");
		}
		else {
			for(Film filmFile : filmDB.getFilmDB()) {
				for(String criteria : splitCriteria) {
					//SEARCHING FILM RATINGS
					if(criteria.matches("[0-9]{1}")) { //Like Release Date Algorithm, but only searches one number.
						if(filmFile.getFilmRating() == Integer.parseInt(criteria)) {
							database.addFilm(filmFile);
						}
					}
					//SEARCHING RELEASE DATES. Algorithm only finds criteria containing 4 consecutive Numbers.
					if(criteria.matches("[0-9]{4}")) {
						if(filmFile.getReleaseDate() == Integer.parseInt(criteria)) {
							database.addFilm(filmFile);
						}
					}
					if(criteria.matches("[A-Za-z0-9 !?&]+")) { {
						//SEARCHING FILM NAMES
						if(filmFile.getFilmName().contains(criteria)) {
							database.addFilm(filmFile);
						}
						//SEARCHING GENRES
						if(filmFile.getFilmGenre().getGValues().contains(criteria.toUpperCase())) {
							database.addFilm(filmFile);
						}
						//SEARCHING CAST LISTS
						if(!filmFile.getFilmCastList().isEmpty()){ //Searches CastLists for criteria.
							for(String actor : filmFile.getFilmCastList()) {
								if(actor.contains(criteria)) {
									database.addFilm(filmFile);
								}
							}
						}
					}
					}
					//PROMPTS USER IF NO RESULTS COULD BE FOUND OR CRITERIA NOT ENTERED
					else if (database.getFilmDB().size() == 0){
						System.out.println("No results found...");
					}
				}
			}
		} return database;
	}

	//CONSTRUCTOR and GET
	public SearchEngine(FilmDatabase sourceFilmDB) {
		this.filmDB = sourceFilmDB;
	}

	public FilmDatabase getFilmDB() {
		return filmDB;
	}
}
