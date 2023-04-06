package classes.sort_algorithm;

public class Quicksort {
    long contComparacoes = 0;
    long contItensModificados = 0;

    public int[] sort(int[] vetor, int inicio, int fim) {

        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            sort(vetor, inicio, posicaoPivo - 1);
            sort(vetor, posicaoPivo + 1, fim);
        }
        return vetor;
    }

    private int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int i = inicio + 1, f = fim;

        while (i <= f) {

            contComparacoes++;
            if (vetor[i] <= pivo) {
                i++;

            } else if (pivo < vetor[f]) {
                f--;
            } else {
                contItensModificados++;
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;

                if (i == f) {

                }
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

    public void showComparations() {
        System.out.println("Número de comparações: " + contComparacoes);
        System.out.println("Número de modificações: " + contItensModificados);
    }
}
