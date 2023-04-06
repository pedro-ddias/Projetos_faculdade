package Padroes;

import java.util.ArrayList;

public class Vertice {
    private int id;
    private String materia;
    private int grupo;
    private ArrayList<Aresta> arestas;
    private ArrayList<Aluno> alunos;

    public Vertice(int id, String nome) {
        this.id = id;
        this.materia = nome;
        this.grupo = id;
        this.arestas = new ArrayList<Aresta>();
        this.alunos = new ArrayList<Aluno>();
    }

    public Vertice(String nome) {
        this.materia = nome;
        this.arestas = new ArrayList<Aresta>();
        this.alunos = new ArrayList<Aluno>();
    }

    public String getNome() {
        return this.materia;
    }

    /**
     * TEM QUE FAZER DIREITO
     * 
     * CADA VÉRTICE TERÁ VÁRIAS ARESTAS
     * 
     * @param i
     * @return
     */
    public Aresta getAresta(int i) {
        return arestas.get(i);
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }

    public void setNome(String nome) {
        this.materia = nome;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getGrupo() {
        return this.grupo;
    }

    public ArrayList<Aresta> getListaArestas() {
        return this.arestas;
    }

    /**
     * Cria uma aresta
     * 
     * @param aresta
     */
    public void addAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }

    /**
     * Adiciona um novo aluno na matéria
     * 
     * @param aluno
     */
    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    /**
     */
    @Override
    public String toString() {
        String str = "";
        str += Integer.toString(this.id + 1);
        str += " Grupo: ";
        str += Integer.toString(this.grupo);
        return str;

    }

}
