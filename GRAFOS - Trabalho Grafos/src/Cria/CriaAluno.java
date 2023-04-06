package Cria;

import Padroes.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CriaAluno {
    // CRIAR ARRAYLIST DE ALUNOS E DEPOIS INSERIR CADA UM EM SEU DEVIDO VÉRTICE

    private int numAlunos;

    public static void CriaAluno(Grafo grafo, String arquivo) throws FileNotFoundException {
        Scanner lerArquivo = new Scanner(new File(arquivo));

        while (lerArquivo.hasNextLine()) {
            String linhaAtual = lerArquivo.nextLine();
            String[] dados = linhaAtual.split(" ");

            int id = Integer.parseInt(dados[0]);
            int materia = Integer.parseInt(dados[1]);
            Vertice vertice = grafo.getVertice(materia - 1);
            Aluno aluno = new Aluno(id, materia);
            vertice.addAluno(aluno);
        }
    }
}