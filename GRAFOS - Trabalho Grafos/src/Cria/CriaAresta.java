package Cria;

import Padroes.*;
import java.io.FileNotFoundException;

public class CriaAresta {

    public static void CriaAresta(Grafo grafo, String[][] matriz) throws FileNotFoundException {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i == j)
                    continue;
                int peso = Integer.parseInt(matriz[i][j]);
                Vertice v1 = grafo.getVertice(i);
                Vertice v2 = grafo.getVertice(j);
                Aresta aresta = new Aresta(peso, v1, v2);
                grafo.addAresta(aresta);
            }
        }

    }
}
