package Padroes;

import java.util.ArrayList;

public class Cluster {

    private int numVertices;
    private Grafo cluster;
    private int grauDissimilaridade;
    private int numAlunostotal;
    private int numProfs;

    public Cluster() {
        this.cluster = new Grafo();
        this.numVertices = 0;
        this.grauDissimilaridade = 0;
        this.numAlunostotal = 0;
        this.numProfs = 0;
    }

    public Cluster(Grafo grafo, int grupo) {
        this.cluster = new Grafo();
        this.numVertices = 0;
        this.grauDissimilaridade = 0;
        this.numAlunostotal = 0;
        for (Vertice vertice : grafo.vertices) {
            if (vertice.getGrupo() == grupo) {
                this.cluster.vertices.add(vertice);
                this.numVertices++;
                this.numAlunostotal += vertice.getAlunos().size();
                for (Aresta aresta : vertice.getListaArestas()) {
                    boolean adicionou = this.cluster.addAresta(aresta);
                    if (adicionou)
                        this.grauDissimilaridade += aresta.getPeso();
                }
            }
        }

    }

    /**
     * Método para verificar se o Cluster está vazio.
     * @return boolean
     */
    public boolean isEmpty() {
        if (this.cluster.vertices.size() <= 0)
            return true;

        return false;
    }

    public void addProf(int prof) {
        this.numProfs += prof;
    }
    
    public int getAlunos() {
        return this.numAlunostotal;
    }
    
    @Override
    public String toString() {
        return ("Grupo: " + this.cluster.getVertice(0).getGrupo() + "\nNúmero de profs: " + this.numProfs
                + "| Número de vértices: " + this.numVertices
                + " | Grau Dissimilaridade: " + this.grauDissimilaridade
                + " | Número total alunos: " + this.numAlunostotal);
    }
}
