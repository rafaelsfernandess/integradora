package br.com.empresa;

import br.com.empresa.exception.BOException;
import br.com.empresa.view.ConsultaBibliaView;



public class Principal {
	public static void main(String[] args)  {
		try {
			ConsultaBibliaView frame = new ConsultaBibliaView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
