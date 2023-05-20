package controller;

import java.io.IOException;

import br.edu.fateczl.filaobj.Fila;
import model.Aluno;

public interface IArquivosController {

	// public void readDir(String path) throws IOException;
	public void createFile(String path, String nome, Fila f) throws Exception;
	
}
