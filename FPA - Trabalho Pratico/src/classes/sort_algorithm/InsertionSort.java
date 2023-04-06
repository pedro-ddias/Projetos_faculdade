package classes.sort_algorithm;

public class InsertionSort {
    
	long contComparacoes = 0;
	long contItensModificados = 0;

    public int[] sort(int[] vetor){
		int aux;
        for (int i = 1; i < vetor.length; i++) {
			
            for(int j = i ; j > 0 ; j--){
				contComparacoes++;
                if(vetor[j] < vetor[j-1]){
					contItensModificados++;
                    aux = vetor[j];
                    vetor[j] = vetor[j-1];
                    vetor[j-1] = aux;
                }
            }
        }
		return vetor;
    }

	public void showComparations() {
        System.out.println("Número de comparações: " + contComparacoes);
        System.out.println("Número de modificações: " + contItensModificados);
    }
}
