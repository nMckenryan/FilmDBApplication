package filmDatabaseApp;

import java.awt.EventQueue;

/**
 * FilmForm is a small window that pops up when 
 * the 'Add Film' button is pressed in the main GUI.
 * With this window, the user can input the information 
 * about a film to be stored in the main Database.
 * 
 * This class defines the methods used to build the 
 * FilmForm window and to save a new Film when all 
 * the appropriate criteria has been entered.
 * 
 * @author Nigel Mckenzie-Ryan
 * @version 1.0
 */

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class FilmForm extends JFrame {

	private JFrame frame;
	private Film filmModel;
	private JTextField nameField;
	private JTextField ratingField;
	private JTextField releaseField;
	private JTextField genreField;
	private JTextField castField;
	private JButton okButton;
	private JButton cancelButton;

	//MAIN CONSTRUCTOR
	public FilmForm() {
		this.filmModel = new Film();

		this.frame = new JFrame();
		frame.setSize(451, 299);
		frame.setBounds(100, 100, 450, 248);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//FILM NAME FIELD
		this.nameField = new JTextField("Film Name");
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nameField.setLocation(87, 10);
		nameField.setSize(259, 25);

		//RATING FIELD
		this.ratingField = new JTextField("5");
		ratingField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ratingField.setLocation(87, 46);
		ratingField.setSize(259, 25);

		//RELEASE DATE FIELD
		this.releaseField = new JTextField("2009");
		releaseField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		releaseField.setLocation(87, 82);
		releaseField.setSize(259, 25);

		//GENRE FIELD
		this.genreField = new JTextField("ACTION");
		genreField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		genreField.setLocation(87, 118);
		genreField.setSize(259, 25);

		//CAST FIELD
		this.castField = new JTextField("Christian Slater, Nicholas Cage");
		castField.setFont(new Font("Tahoma", Font.PLAIN, 9));
		castField.setLocation(87, 154);
		castField.setSize(259, 25);


		//OK BUTTON
		this.okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		okButton.setBounds(356, 10, 68, 25);

		//ADDING ELEMENTS TO FRAME
		frame.getContentPane().add(getNameField());
		frame.getContentPane().add(getRatingField());
		frame.getContentPane().add(getReleaseField());
		frame.getContentPane().add(getGenreField());
		frame.getContentPane().add(getCastField());
		frame.getContentPane().add(getOkButton());

		//LABELS FOR FIELDS (Generated and Positioned with Eclipse WindowBuilder)
		JLabel lblFilmName = new JLabel("Film Name");
		lblFilmName.setBounds(10, 16, 48, 14);
		frame.getContentPane().add(lblFilmName);

		JButton button = new JButton("Cancel");
		button.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button.setBounds(356, 47, 68, 25);
		frame.getContentPane().add(button);

		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(10, 51, 48, 14);
		frame.getContentPane().add(lblRating);

		JLabel lblReleaseDate = new JLabel("Release Date");
		lblReleaseDate.setBounds(10, 87, 64, 14);
		frame.getContentPane().add(lblReleaseDate);

		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(10, 123, 48, 14);
		frame.getContentPane().add(lblGenre);

		JLabel lblCastList = new JLabel("Cast List");
		lblCastList.setBounds(10, 159, 48, 14);
		frame.getContentPane().add(lblCastList);
	}

	//GETTERS AND SETTERS 
	public Film getFilmModel() {
		return filmModel;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getRatingField() {
		return ratingField;
	}

	public JTextField getReleaseField() {
		return releaseField;
	}

	public JTextField getGenreField() {
		return genreField;
	}

	public JTextField getCastField() {
		return castField;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}