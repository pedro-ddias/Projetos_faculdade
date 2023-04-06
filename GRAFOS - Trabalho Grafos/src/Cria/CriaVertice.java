package Cria;

import java.io.*;
import Padroes.Grafo;
import Padroes.Vertice;
public class CriaVertice {

    public static void CriaVertice(Grafo grafo) throws IOException {
        BufferedReader lerArquivo = new BufferedReader(new FileReader("src/areaPesquisaNome.txt"));

        int auxVertice = 0;
        String proximaLinha;
        while ((proximaLinha = lerArquivo.readLine()) != null) {

            Vertice vertice = new Vertice(auxVertice, proximaLinha);

            grafo.addVertice(vertice);
            auxVertice++;

        }

    }
}