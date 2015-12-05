package finalproject;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This verifies is a text field is in the correct date format. It is used because SimpleDateFormat and JFormattedTextFields do not sync well between
 * setting values via setValue() and setText() which can cause issues.
 * 
 * https://halleysweblog.wordpress.com/2009/04/08/data-validation-in-java-swing/
 * @author dhruv
 *
 */

public class DateVerifier extends InputVerifier {
	
	private String re="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";
	@Override
	public boolean verify(JComponent input) {
		JTextField tfDob=(JTextField)input;
		if(tfDob.getText().equals("") | tfDob.getText().matches(re))
		{return true;
		}
		else
		{
		JOptionPane.showMessageDialog(input, "You entered an invalid date",
		"Incorrect Submission!", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	}
	
	
	
	
	
}
