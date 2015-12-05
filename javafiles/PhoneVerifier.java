package finalproject;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * 
 * Allows any component to be verified against a regex of UK phone numbers (in my case the tfEmergency Phone variable, stripping white spaces first).
 *
 */
public class PhoneVerifier extends InputVerifier {
	
	private String re="0800[0-9]{6}|07[0-9]{9}|0845[0-9]{6}";

	@Override
	public boolean verify(JComponent input) {
		JTextField tfEmergencyPhone=(JTextField)input;
		if(tfEmergencyPhone.getText().equals("") | tfEmergencyPhone.getText().matches(re))
		{return true;
		}
		else
		{
			JOptionPane.showMessageDialog(input, "You entered an invalid number. Try again.",
					"Incorrect Submission!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
