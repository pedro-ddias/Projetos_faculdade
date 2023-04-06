package Elemento;

import Arvore.HeapMinExercicios;

public class ExercicioElement {
    public HeapMinExercicios exercicio;
    public ExercicioElement proximo;

    public ExercicioElement(HeapMinExercicios exercicio) {
        this.exercicio = exercicio;
        this.proximo = null;
    }

    @Override
    public boolean equals(Object obj) {
        String aux = (String) obj;
        return exercicio.equals(aux);
    }
}