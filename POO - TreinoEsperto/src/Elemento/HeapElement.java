package Elemento;

import Arvore.HeapMinExercicios;
import Dados.Exercicio;

public class HeapElement {
    public HeapMinExercicios exercicioHeap;
    public HeapElement proximo;

    public HeapElement(Exercicio exercicio) {
        this.exercicioHeap = new HeapMinExercicios();
        exercicioHeap.insere(exercicio);
        this.proximo = null;
    }

    @Override
    public boolean equals(Object obj) {
        String aux = (String) obj;
        return exercicioHeap.equals(aux);
    }
}