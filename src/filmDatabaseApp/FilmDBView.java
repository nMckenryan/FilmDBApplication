package filmDatabaseApp;

import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

/**
 * FilmDBView declares the methods and GUI Elements to be initialised by the FilmDBController.
 * 
 * @author Nigel Mckenzie-Ryan
 * @version 1.0
 */

public class FilmDBView extends JFrame {

	private FilmDatabase model;
	private JTable mainTable;
	private JTextField filterText;
	private JButton addButton;
	private JButton removeButton;
	private JButton searchButton;

	/** 
	 * INITIALISES THE TABLE AT STARTUP
	 * @param a FilmDatabase to be converted to a JTable Layout (will be inputted from .txt file)
	 * @return a model to be used for setting up the JTable Layout
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */ 
	public JTable initialiseList(FilmDatabase filmList) {	
		Object columnNames[] = { "Title", "Rating", "Year", "Genre", "Cast" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		for(Film film : filmList.getFilmDB()) 
		{
			Vector<Object> row = new Vector<Object>(5);
			row.addElement(film.getFilmName());
			row.addElement(film.getFilmRating());
			row.addElement(film.getReleaseDate());
			row.addElement(film.getFilmGenre());
			row.addElement(film.getFilmCast());
			model.addRow(row);
		}

		JTable table = new JTable(model);
		return table;
	}

	/** 
	 * ALGORITHM FOR SEARCH RESULTS - Similar to initialiseList, but returns a Filtered Database.
	 * @param a String that will be used as search criteria for .search()
	 * @return a model to be used for setting up the JTable Layout, filtered by criteria.
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */ 
	public JTable searchResults(String criteria) {
		FilmDatabase baseFilmList = new FilmDatabase();
		baseFilmList.listInput("mainDBTest.txt");
		FilmDatabase filmList = new FilmDatabase();
		filmList = baseFilmList.getSearchEng().search(criteria);

		Object columnNames[] = { "Title", "Rating", "Year", "Genre", "Cast" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		for(Film film : filmList.getFilmDB()) 
		{
			Vector<Object> row = new Vector<Object>(5);
			row.addElement(film.getFilmName());
			row.addElement(film.getFilmRating());
			row.addElement(film.getReleaseDate());
			row.addElement(film.getFilmGenre());
			row.addElement(film.getFilmCast());
			model.addRow(row);
		}

		JTable table = new JTable(model);
		return table;
	}

	/** 
	 * Removing Selected Results from Film List.
	 * @param a Table that will be updated (mainTable)
	 * @return null. Will find selected recod, remove it and update the Film List.
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */ 
	public void removeResults(JTable table){
		DefaultTableModel model = (DefaultTableModel) this.mainTable.getModel();
		int[] selectedRows = table.getSelectedRows();
		for(int rowCount = 0; rowCount < selectedRows.length; rowCount++){
			model.removeRow(selectedRows[rowCount]-rowCount);
		}
	}

	/** 
	 * Updating the Table once changes are made.
	 * @param void
	 * @return void
	 * @author Nigel Mckenzie-Ryan 1250670
	 * */ 
	public void updateTable() {
		Object columnNames[] = { "Title", "Rating", "Year", "Genre", "Cast" };
		DefaultTableModel defModel = new DefaultTableModel(columnNames, 0);
		for(Film film : model.getFilmDB()) 
		{
			Vector<Object> row = new Vector<Object>(5);
			row.addElement(film.getFilmName());
			row.addElement(film.getFilmRating());
			row.addElement(film.getReleaseDate());
			row.addElement(film.getFilmGenre());
			row.addElement(film.getFilmCast());
			defModel.addRow(row);
		}
		mainTable.setModel(defModel);
	}

	//MAIN CONSTRUCTOR	
	public FilmDBView(FilmDatabase model) {
		this.model = model;
		getContentPane().setLayout(null);

		//ADD FILM BUTTON
		this.addButton = new JButton("Add Film");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		addButton.setLocation(363, 10);
		addButton.setSize(100, 25);

		//SEARCH FIELD AND BUTTON
		this.filterText = new JTextField("");
		filterText.setLocation(10, 10);
		filterText.setSize(233, 25);

		this.searchButton = new JButton("Search Films");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		searchButton.setBounds(253, 10, 100, 25);

		//REMOVE BUTTON
		this.removeButton = new JButton("Remove Film");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		removeButton.setBounds(473, 10, 100, 25);
	}

	//GETTERS AND SETTERS

	public FilmDatabase getModel() {
		return model;
	}

	public JTable getMainTable() {
		return mainTable;
	}

	public void setMainTable(JTable mainTable) {
		this.mainTable = mainTable;
	}

	public JTextField getFilterText() {
		return filterText;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}
}
