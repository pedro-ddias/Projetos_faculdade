package Algoritmo;

import java.util.*;

import Padroes.Aresta;
import Padroes.Vertice;
import Padroes.Grafo;

public class Kruskal {
	private ArrayList<Vertice> verticesKruskal;
	private ArrayList<Aresta> arestasKruskal;
	private Grafo kruskal;

	public Kruskal(Grafo grafo) {
		this.verticesKruskal = grafo.vertices;
		this.arestasKruskal = grafo.arestas;
		Collections.sort(arestasKruskal);
		this.kruskal = new Grafo();

		for (Vertice vertice : verticesKruskal) {
			this.kruskal.addVertice(vertice);
		}

		for (Aresta aresta : arestasKruskal) {
			if (aresta.getV1().getGrupo() != aresta.getV2().getGrupo()) {

				Vertice v1 = aresta.getV1();
				Vertice v2 = aresta.getV2();
				v1.addAresta(aresta);
				v2.addAresta(aresta);
				this.kruskal.addAresta(aresta);
				unir(aresta.getV1(), aresta.getV2());
			}
		}
		// System.out.println(kruskal.toString());
	}

	public ArrayList<Vertice> getVertices() {
		return this.kruskal.vertices;

	}

	public ArrayList<Aresta> getArestas() {
		return this.kruskal.arestas;

	}

	public Grafo getGrafo() {
		return this.kruskal;
	}

	public void unir(Vertice v1, Vertice v2) {
		if (v1.getGrupo() < v2.getGrupo()) { // Testa se o grupo de V1 é menor que o grupo de V2
			int aux = v2.getGrupo();
			v2.setGrupo(v1.getGrupo()); // Inclui V2 no grupo de V1
			for (Vertice vertice : verticesKruskal) {
				if (vertice.getGrupo() == aux) {
					vertice.setGrupo(v1.getGrupo());
				}
			}
		} else if (v1.getGrupo() > v2.getGrupo()) {
			int aux2 = v1.getGrupo();
			v1.setGrupo(v2.getGrupo());
			for (Vertice vertice : verticesKruskal) {
				if (vertice.getGrupo() == aux2) {
					vertice.setGrupo(v2.getGrupo());
				}
			}
		}
	}

	public void separar(Aresta aresta) {
		Vertice v1 = aresta.getV1();
		Vertice v2 = aresta.getV2();

		int grupoNovoV2 = v2.getId();
		v2.setGrupo(grupoNovoV2);
		int grupoNovoV1 = v1.getId();

		for (int i = 0; i < v2.getListaArestas().size(); i++) {
			Aresta aresta2 = v2.getAresta(i);
			Vertice aux2 = aresta2.getV2();
			aux2.getListaArestas().remove(aresta);
			this.kruskal.arestas.remove(aresta);
			juntaGrupo(aux2, grupoNovoV2);
		}

		for (int i = 0; i < v1.getListaArestas().size(); i++) {
			Aresta aresta2 = v1.getAresta(i);
			Vertice aux1 = aresta2.getV1();
			aux1.getListaArestas().remove(aresta);
			juntaGrupo(aux1, grupoNovoV1);
		}
	}

	public void juntaGrupo(Vertice vertice, int grupoNovo) {
		vertice.setGrupo(grupoNovo);
		for (Aresta aresta : vertice.getListaArestas()) {
			if (aresta.getV1().getGrupo() != vertice.getGrupo())
				juntaGrupo(aresta.getV1(), grupoNovo);
			if (aresta.getV2().getGrupo() != vertice.getGrupo())
				juntaGrupo(aresta.getV2(), grupoNovo);
		}

	}

	public String toString() {
		return this.kruskal.toString();
	}
}
