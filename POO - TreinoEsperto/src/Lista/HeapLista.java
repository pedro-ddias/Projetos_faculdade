package Lista;

import Hash.HashExercicio;
import Dados.Exercicio;
import Elemento.HeapElement;

public class HeapLista {
    public static Object heapLista;
    public HeapElement primeiro;
    public HeapElement ultimo;

    public HeapLista() {
        primeiro = new HeapElement(null);
        ultimo = primeiro;
    }

    public void inserir(Exercicio exercicios) {
        int index = HashExercicio.funcaoHash(exercicios.cpf);
        HeapLista lista = HashExercicio.tabela[index];
        HeapElement aux = lista.primeiro.proximo;

        if (aux != null) {
            while (aux != null) {
                if (aux.exercicioHeap.get(0).cpf.equals(exercicios.cpf)) {
                    aux.exercicioHeap.insere(exercicios);
                    return;

                } else {
                    aux = aux.proximo;
                }
            }
        }

        HeapElement novo = new HeapElement(exercicios);
        ultimo.proximo = novo;
        ultimo = novo;
    }

}
