package Cria;

import Dados.Atleta;
import Hash.HashAtleta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe de inserção de dados nas estruturas -Retirada de dados do txt
 */
public class CriaAtleta {
    public static HashAtleta criaAtleta() throws FileNotFoundException {
        Scanner lerArquivo = new Scanner(new File("Atleta.txt"));
        HashAtleta atletaHash = new HashAtleta();

        while (lerArquivo.hasNextLine()) {
            String linhaAtual = lerArquivo.nextLine();
            String[] dados = linhaAtual.split(";");
            Atleta atleta = new Atleta(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]);
            atletaHash.addTabela(atleta);
        }

        lerArquivo.close();
        return atletaHash;
    }
}
