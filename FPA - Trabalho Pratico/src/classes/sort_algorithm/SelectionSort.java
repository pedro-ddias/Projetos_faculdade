package classes.sort_algorithm;

public class SelectionSort {
    long contComparacoes = 0;
    long contItensModificados = 0;
    public int[] sort(int[] array){


        for (int fixo = 0; fixo < array.length - 1; fixo++) {
            int menor = fixo;

            for (int i = menor + 1; i < array.length; i++) {
                
                contComparacoes++;
                if (array[i] < array[menor]) {
                    contItensModificados++;
                    menor = i;
                }
            }
            if (menor != fixo) {
                int t = array[fixo];
                array[fixo] = array[menor];
                array[menor] = t;
            }
        }
        return array;
    }

    public void showComparations() {
        System.out.println("Número de comparações: " + contComparacoes);
        System.out.println("Número de modificações: " + contItensModificados);
    }
}
