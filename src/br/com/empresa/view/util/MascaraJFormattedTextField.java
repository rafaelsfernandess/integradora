package br.com.empresa.view.util;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 * Classe responsável por fazer a formatação de componentes.
 * 
 * Olhar o link abaixo
 * 
 * http://www.java2s.com/Tutorial/Java/0240__Swing/Charactersyoucanuseintheformattingmask.htm
 */
public class MascaraJFormattedTextField {

	public static void formatNumericField(JFormattedTextField jFormattedTextField) {

		formatNumericField("#,###.00", jFormattedTextField);

	}

	public static void formatNumericField(String mask, JFormattedTextField jFormattedTextField) {

		NumberFormatter numberFormatter = new NumberFormatter(new java.text.DecimalFormat(mask));
		jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(numberFormatter));

	}

	public static void formatField(String mask, JFormattedTextField jFormattedTextField) {

		jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(createFormatter(mask)));
		//Deve ser setado para nulo senão dará problema na hora da troca da formatação.
		jFormattedTextField.setValue(null);
	}

	private static MaskFormatter createFormatter(String format) {

		MaskFormatter formatter = null;

		try {
			formatter = new MaskFormatter(format);
			//formatter.setPlaceholderCharacter(' '/* ou '0' etc*/);
			formatter.setAllowsInvalid(false); // se necessário
			formatter.setOverwriteMode(true); // se necessário
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}

}
