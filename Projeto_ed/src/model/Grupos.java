package model;

import java.util.Objects;

import javax.swing.JOptionPane;

import br.edu.fateczl.lista.listaObj.Lista;

public class Grupos {

	public int idGrupo;
	public Lista integrantesGrupo;
	public String subArea;
	public String orientador;
	public String tema;
	
	
	@Override
	public int hashCode() {
		switch(subArea) {
		case "Redes":
			return 0;
		case "Dados":
			return 1;
		case "Seguranca":
			return 2;
		case "Engenharia":
			return 3;
		default:
			return 4;
		}
	}
	
	
}
