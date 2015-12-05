package hospitalsystem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class RegExpInputVerifier extends InputVerifier {
	
	
        private String expression;

        public RegExpInputVerifier(String expression) {
            this.expression = expression;
        }

        public String getExpression() {
            return expression;
        }

	
	@Override
	public boolean verify(JComponent input) {
		boolean verified = false;
        if (input instanceof JTextComponent) {
            JTextComponent field = (JTextComponent) input;
            String regNo1 = field.getText();
            Pattern pattern1 = Pattern.compile(expression);
            Matcher matcher1 = pattern1.matcher(regNo1);
        }
		return false;
	}

}
