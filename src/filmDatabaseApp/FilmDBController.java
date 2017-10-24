package filmDatabaseApp;

import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * FilmDBController initialises and controls 
 * the application's view methods.
 * 
 * @author Nigel Mckenzie-Ryan
 * @version 1.0
 */

public class FilmDBController extends JFrame{

	private FilmDatabase model;
	private FilmDBView view;
	private FilmForm fView = new FilmForm();

	public FilmDBController(String name) {
		super(name);
		//CREATING MODEL + VIEW
		//this.model = new FilmDatabase().listInput("mainDBTest.txt");;
		this.view = new FilmDBView(this.model);

		getContentPane().setLayout(null);	
		view.setMainTable(view.initialiseList(model));

		//SETTING UP SCROLLPANE
		JScrollPane scrollPane = new JScrollPane(view.getMainTable(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLocation(10, 46);
		scrollPane.setSize(574, 414);
		getContentPane().add(scrollPane);

		//ADDING THE VIEW
		getContentPane().add(view.getAddButton());
		getContentPane().add(view.getFilterText());
		getContentPane().add(view.getSearchButton());
		getContentPane().add(view.getRemoveButton());


		//EVENT LISTENERS

		//REMOVING A SELECTED FILM FROM DATABASE -works only superficially. deleted item reappears
		view.getRemoveButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.removeResults(view.getMainTable());
			}
		});

		//PERFORMING A SEARCH - This method collects criteria entered in Search field and updates table with results. - Does not work. refreshes list.
		view.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = model.getSearchEng().search(view.getFilterText().getText());
				view.updateTable();
			}
		});

		//LAUNCHING THE ADDFILM WINDOW (FilmForm) (Does not work. Opens window, but is very small.)
		view.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilmForm form = new FilmForm();
				form.setVisible(true);
			}
		});

		fView.getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Getting Film Name, Rating, Release Date, Genre and Cast from TextFields.
				String newFName = fView.getNameField().getText();
				int newFRating = Integer.parseInt(fView.getRatingField().getText());
				int newFRelease = Integer.parseInt(fView.getReleaseField().getText());
				Genre newFGenre = Genre.valueOf(fView.getGenreField().getText());

				//Input Validation for Film Addition
				if(!newFName.isEmpty() && !fView.getRatingField().getText().isEmpty() && !fView.getReleaseField().getText().isEmpty() 
						&& !fView.getGenreField().getText().isEmpty() && !fView.getCastField().getText().isEmpty()) {

					Film addingFilm = new Film(newFName, newFRating, newFRelease, newFGenre);
					addingFilm.addCast(fView.getCastField().getText());
					model.addFilm();
				}
			}
		});

		//SAVES DATABASE TO .TXT FILE WHEN WINDOW CLOSES (does not work. Don't know where to call method)
		view.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.out.println("WindowListener method called: windowClosing.");
				FilmDatabase.listOutput(model, "mainDBTest.txt");
				System.exit(1);
			}
		});

		//ENDING PROGRAM AND SIZING
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);
		setResizable(false);
	}

	public static void main(String[] args) {
		JFrame frame = new FilmDBController("Film Database");
		frame.setVisible(true);
	}
}
