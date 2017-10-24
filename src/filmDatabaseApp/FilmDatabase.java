package filmDatabaseApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * FilmDatabase builds a ArrayList of Films and
 * defines the methods to output the list to a .txt file
 * as well as read in the contents of a .txt file (that is 
 * in a proper format).
 * 
 * @author Nigel Mckenzie-Ryan
 * @version 1.0
 */

public class FilmDatabase implements Serializable {
	private ArrayList<Film> filmDB;
	private SearchEngine searchEng;

	//CONSTRUCTORS
	public FilmDatabase() {
		super();
		this.filmDB = new ArrayList<Film>();
		this.searchEng = new SearchEngine(this);
	}

	//ADDING TO AND REMOVING FILMS FROM  DATABASE
	public void addFilm(Film... groupOfFilms) {	
		for(int i = 0; i < groupOfFilms.length; i++) {
			this.filmDB.add(groupOfFilms[i]);
		}
	}

	public void removeFilm(Film purgedFilm) {
		Film rFilm = new Film();
		for(Film foundFilm : filmDB) {
			if(purgedFilm == foundFilm) {
				rFilm = foundFilm;
			}
		}
		this.filmDB.remove(rFilm);
	}

	public String toString() {
		String dbString = "";
		for(Film film : filmDB) {
			dbString += film + " \n";
		}
		return dbString;
	}

	/** 
	 * FILE OUTPUT METHOD - Prints FilmDatabase to a .txt File.
	 * @param The filmDatabase to be written to file and a STtring containing the Filename
	 * @return void, creates new .txt file named after fileName that contains the FilmDatabase.
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */ 
	public static void listOutput(FilmDatabase filmDBList, String fileName) {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(fileName));
			for(Film filmObj : filmDBList.filmDB) {
				output.println(filmObj);
			}
			output.close();
		} catch (IOException error) 
		{
			//Shows error if File cannot be outputed.
			System.err.println("An Output error occured.");
			error.printStackTrace();
		}
	}

	/** 
	 * FILE INPUT METHOD - Converts formatted .txt file to a FilmDatabase.
	 * @param String denoting the name of file to be inputted.
	 * @return FilmDatabase taken from text File.
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */

	public FilmDatabase listInput(String inputFileName) {
		try {
			FilmDatabase printDB = new FilmDatabase();
			Scanner input = new Scanner(new FileReader(inputFileName));
			input.useDelimiter("\n"); //Delimits Array by Line. New Line = New Film Record.

			while(input.hasNextLine()) {
				//Film Format example: Apocalypse Now!;5;1979;DRAMA;Martin Sheen;Lawrence Fishbourne;Marlon Brando;; 
				String[] splitLine = input.nextLine().split(";"); //Splits input into character array, delimited by ";"
				Film printFilm = new Film(splitLine[0], Integer.parseInt(splitLine[1]),
						Integer.parseInt(splitLine[2]), Genre.valueOf(splitLine[3].toUpperCase().trim()));
				for(int castCount = 4; castCount < splitLine.length; castCount++) { //All Strings past splitLine[3] are cast members.
					printFilm.addCast(splitLine[castCount]);
				}
				printDB.addFilm(printFilm);
			}
			input.close();
			return printDB;

		} catch (FileNotFoundException e) {	
			//Shows error message in case of No File.
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		return null;
	}

	//GETTERS AND SETTERS
	public ArrayList<Film> getFilmDB() {
		return filmDB;
	}

	public void setFilmDB(ArrayList<Film> filmDB) {
		this.filmDB = filmDB;
	}

	public SearchEngine getSearchEng() {
		return this.searchEng;
	}
}
