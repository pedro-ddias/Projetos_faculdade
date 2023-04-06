package Padroes;

public class Aresta implements java.lang.Comparable<Aresta> {

    private int peso;
    private Vertice v1;
    private Vertice v2;
    private int grauAresta;

    public Aresta(int peso, Vertice inicio, Vertice fim) {
        this.peso = peso;
        this.v1 = inicio;
        this.v2 = fim;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setV1(Vertice inicio) {
        this.v1 = inicio;
    }

    public void setV2(Vertice fim) {
        this.v2 = fim;
    }

    public Vertice getV1() {
        return this.v1;
    }

    public Vertice getV2() {
        return this.v2;
    }

    public int getGrauAresta() {
        return this.grauAresta;
    }

    public void setGrauAresta() {
        this.grauAresta = v1.getListaArestas().size() + v2.getListaArestas().size();

    }

    /**
     * Método para comparar a aresta atual com a aresta passada como parâmetro.
     * @param aresta
     * @return boolean
     */
    public boolean equals(Aresta aresta) {
        Vertice vinicial = aresta.getV1();
        Vertice vfinal = aresta.getV2();

        if (((vinicial.equals(this.v1)) && (vfinal.equals(this.v2)) && aresta.getPeso() == this.peso)
                || (vinicial.equals(this.v2) && vfinal.equals(this.v1) && aresta.getPeso() == this.peso))
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Aresta aresta) {
        if (this.peso == aresta.getPeso())
            return 0;
        if (this.peso > aresta.getPeso())
            return 1;

        return -1;
    }

    public boolean comparar(Aresta aresta) {
        if (this.peso == aresta.getPeso() && this.v1.equals(aresta.getV1()) && this.v2.equals(aresta.getV2()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return ("| Peso: " + this.peso + " | Origem: " + this.v1.toString() + " Fim: " + this.v2.toString());
    }
}
