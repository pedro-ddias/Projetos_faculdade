package Cria;

import Dados.Exercicio;
import Hash.HashExercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe de inserção de dados nas estruturas -Retirada de dados do txt
 */
public class CriaExercicio {
    public static HashExercicio criaExercicio() throws FileNotFoundException {
        Scanner lerArquivo = new Scanner(new File("Exercicio.txt"));
        HashExercicio exerciciosHash = new HashExercicio();
        while (lerArquivo.hasNextLine()) {
            String linhaAtual = lerArquivo.nextLine();
            String[] dados = linhaAtual.split(";");
            Exercicio exercicio = new Exercicio(dados[0], dados[1], dados[2]);
            exerciciosHash.addTabela(exercicio);
        }
        lerArquivo.close();
        return exerciciosHash;
    }
}
