package Cria;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CriaMatriz {

    public String[][] CriaMatriz() throws FileNotFoundException {
        Scanner lerArquivo = new Scanner(new File("src/matrizDissimilaridade.txt"));

        String[][] matriz = new String[20][20];
        int linha = 0;
        int colunaInicio = 0;

        while (lerArquivo.hasNextLine()) {
            int coluna = colunaInicio;
            String linhaAtual = lerArquivo.nextLine();
            String linhaFinal = linhaAtual;

            int index = linhaAtual.indexOf("0");
            linhaFinal = linhaAtual.substring(index);

            String[] dados = linhaFinal.split(" ");

            for (int i = 0; i < dados.length; i++) {
                matriz[linha][coluna] = dados[i];
                coluna++;
            }
            linha++;
            colunaInicio++;
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                matriz[j][i] = matriz[i][j];
            }
        }
        // for (int i = 0; i < 20; i++) {
        // for (int j = 0; j < 20; j++) {
        // System.out.print(matriz[i][j] + " ");
        // }
        // System.out.println(" ");
        // }

        return matriz;
    }
}
