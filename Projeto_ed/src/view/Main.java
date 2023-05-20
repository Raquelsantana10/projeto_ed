package view;

import controller.ArquivosController;

public class Main {

	public static void main(String[] args) {

		ArquivosController aq = new ArquivosController();
		
		try {
			aq.registrarGrupo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
