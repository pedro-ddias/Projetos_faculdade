package Padroes;
import java.util.ArrayList;

public class Grafo {

    // Listas de vertices e arestas.
    public ArrayList<Vertice> vertices;
    public ArrayList<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();

    }

    /**
     * Adiciona um vértice ao grafo
     * 
     * @param vertice
     */
    public void addVertice(Vertice vertice) {
        this.vertices.add(vertice);
    }

    public void addTodosVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void addTodasArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    /**
     * Adiciona uma aresta aos vértices em que essa aresta se encontra, e logo após
     * isso add as arestas ao grafo
     * 
     * @param aresta
     */
    public boolean addAresta(Aresta aresta) {
        boolean igual = false;
        for (Aresta aresta2 : this.arestas) {
            igual = aresta2.comparar(aresta);
            if (igual == true)
                break;
        }
        if (igual == false) {
            this.arestas.add(aresta);
            return true;
        }
        return false;
    }

    /**
     * Pega nome do vertice passado como parâmetro.
     * 
     * @param index
     * @return
     */
    public Vertice getVertice(int index) {
        return this.vertices.get(index);
    }

    /**
     * Recebe um vértice e retorna o index dele na lista de vértices do grafo
     * 
     * @param vertice2
     * @return
     */
    public int getIndexVertice(Vertice vertice2) {
        int index = -1;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getNome().equals(vertice2)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.vertices.size(); i++) {
            str += "Vertice: " + this.vertices.get(i).toString() + "\n";

        }
        for (int i = 0; i < this.arestas.size(); i++) {
            str += this.arestas.get(i);
            str += " \n";
        }
        return str;
    }
}
