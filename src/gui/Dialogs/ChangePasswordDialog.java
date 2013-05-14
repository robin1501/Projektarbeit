package gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * 
 * Dialog zur Passwortänderung. 
 * Dieser Dialog wird als PopUp aus dem Panel "Pesönliche Daten" aufgerufen.<br>
 * Es bietet die Möglichkeit das Passwort abzuändern.Hierzu werden 2 Textboxen abgefragt.<br>
 * Das Passwort <b>muss </b>aus Buchstaben und Zahlen keine Sonderzeichen<br><br>
 * Nach den InsertAndValidation Checks wird das neue Passwort direkt abgespeichert,<br>
 * das Hauptfenster geschlossen und das Loginfenster aufgerufen.
 * 
 * 
 *
 */
public class ChangePasswordDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChangePasswordDialog dialog = new ChangePasswordDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangePasswordDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
