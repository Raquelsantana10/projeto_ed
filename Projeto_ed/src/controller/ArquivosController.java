package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import br.edu.fateczl.filaobj.Fila;
import br.edu.fateczl.lista.listaObj.Lista;
import model.Aluno;
import model.Grupos;

public class ArquivosController implements IArquivosController {

	Fila alunosFila = new Fila();
	
	Lista[] vetGrupos;
	
	public ArquivosController() {
		vetGrupos = new Lista[10];
		for (int i = 0; i < 10; i++) {
			vetGrupos[i] = new Lista();
		}
		
	}
	
	public void registarAluno() throws Exception {
		int opc = 1;
		do {
		Aluno aluno = new Aluno();
		aluno.nomeAluno = JOptionPane.showInputDialog("Digite o nome do Aluno");
		aluno.ra = Integer.parseInt(JOptionPane.showInputDialog("Digite o RA do aluno"));
		alunosFila.insert(aluno.nomeAluno + ";" + aluno.ra);
		opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja continuar? \n1 - Sim \n2 - Não"));
		} while (opc == 1);
		String path = "C:\\TEMP";
		String nome = "Alunos.csv";
		createFile(path, nome, alunosFila);
	}
	
	public void registrarGrupo() {
		Grupos grupo = new Grupos();
		int opc = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de integrantes do grupo"));
		do {
			String nome = JOptionPane.showInputDialog("Digite o nome do Aluno");
			grupo.integrantesGrupo = new Lista();
			grupo.integrantesGrupo.addFirst(nome);
			opc--;
		} while (opc > 0);
		
		grupo.idGrupo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Id do grupo"));
		grupo.orientador = JOptionPane.showInputDialog("Digite o nome do orientador");
		grupo.tema = JOptionPane.showInputDialog("Digite o tema do grupo");
		
		int subarea = Integer.parseInt(JOptionPane.showInputDialog("Selecione a Subarea do trabalho do grupo \n0 - Redes \n1 - Dados \n2 - Segurança \n3 - Engenharia \n4 - Programação"));
		switch (subarea) {
		case 0:
			grupo.subArea = "Redes";
		case 1:
			grupo.subArea = "Dados";
		case 2:
			grupo.subArea = "Seguranca";
		case 3:
			grupo.subArea = "Engenharia";
		case 4:
			grupo.subArea = "Programacao";
		}
	}
	
	public void adicionaGrupo(Grupos grupo) throws Exception {
		int hash = grupo.hashCode();
		Lista l = vetGrupos[hash];
		if (l.isEmpty()) {
			l.addFirst(grupo);
		} else {
			l.addLast(grupo);
		}
	}
	

	@Override
	public void createFile(String path, String nome, Fila f) throws Exception {
		File dir = new File(path);
		File arq = new File(path, nome);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			int tamanho = f.size();
			for (int i = 0; i < tamanho; i++) {
			String conteudo = geraTxt(f);
			print.write(conteudo);
			}
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório inválido!");
		}
		
	}

	private String geraTxt(Fila f) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String linha = (String)f.remove();
		buffer.append(linha + "\n");
		return buffer.toString();
	}
}
