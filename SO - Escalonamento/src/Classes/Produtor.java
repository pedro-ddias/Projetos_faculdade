package Classes;

import java.io.*;
import java.util.*;

public class Produtor{

	public Dados[] vetorDados;
	public SyncList vetorSyncList;
	public int[] tipoProduto;

	/**
	 * Cria um objeto esteira, que contém os dados dos pedidos e dos tipos de produtos possíveis
	 */
	public Produtor() {
		this.vetorSyncList = new SyncList();
		carregaPedidos();
		carregaProdutos();
		ordenaPedidos();
	}

	/**
	 * Ordena os pedidos e insere na SyncList
	 */
	public void ordenaPedidos() {
		insertionSort();
		for(int i = 0; i < this.vetorDados.length; i++){
			this.vetorSyncList.addToList(vetorDados[i]);
		}
	}

	/**
	 * Metodo para carregar os dados do arquivo txt. Os pedidos são armazenados no
	 * vetor vetorDados. Os prazos dos pedidos sem prazo são alterados por um valor
	 * grande, para poder ser realizada calculos futuros.
	 */
	private void carregaPedidos() {
		try {
			File file = new File("src\\SO_20_DadosEmpacotadeira_3.txt");
			Scanner arq = new Scanner(file);
			
			String quantString = arq.nextLine();
			quantString = arq.nextLine(); //Tive que ler 2 vezes pois lendo da 1 linha tava dando problema
			int quant = Integer.parseInt(quantString);

			Dados[] vetorDados = new Dados[quant];

			// Lê uma linha do arquivo e separa as informações de cada linha (quebrando pelo ';').
			for (int i = 0; i < quant; i++) {
				String linhaInteira = arq.nextLine();
				String linha[] = linhaInteira.split(";");

				// Se for 0, troca por um valor grande, depois guarda no vetor de objetos.
				if (linha[2].equals("0"))
					linha[2] = Integer.toString(540);
				vetorDados[i] = new Dados(linha[0], Integer.parseInt(linha[1]), Integer.parseInt(linha[2]), Integer.parseInt(linha[3]), Integer.parseInt(linha[4]));
			}

			this.vetorDados = vetorDados;
			arq.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Carrega os tipos de produtos e armazena no vetor
	 */
	private void carregaProdutos() {
		try {
			File file = new File("src\\SO_Produtos.txt");
			Scanner arq = new Scanner(file);
			
			String quantString = arq.nextLine();
			quantString = arq.nextLine(); //Tive que ler 2 vezes pois lendo da 1 linha tava dando problema
			int quant = Integer.parseInt(quantString);

			int[] vetorProd = new int[quant+1];

			// Lê uma linha do arquivo e separa as informações de cada linha (quebrando pelo ';').
			for (int i = 1; i <= quant; i++) {
				String linhaInteira = arq.nextLine();
				String linha[] = linhaInteira.split(";");
				vetorProd[i] = Integer.parseInt(linha[1]);
			}

			this.tipoProduto = vetorProd;
			arq.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Algorítmo de ordenação InsertSort.
	 */
	private void insertionSort() {

		// Ordena primeiro a quantidade de produtos e depois o prazo.
		// - auxComp: auxiliar para comparação com o elemento de referência.
		// - dadoRef: auxiliar para guardar o elemento de referência.
		for (int i = 0; i < 3; i++) {

			// Começa a partir do segundo elemento (posição 1).
			for (int referencia = 1; referencia < this.vetorDados.length; referencia++) {
				int auxComp = referencia - 1;
				Dados dadoRef = this.vetorDados[referencia];

				// Enquanto o elemento de referência for menor e houver vetor, recua uma
				// posição.
				while (auxComp >= 0 && dadoRef.getPedido(i) < this.vetorDados[auxComp].getPedido(i)) {
					auxComp--;
				}

				// Posição encontrada. Copiar todos os elementos entre a posição de referência e
				// o fim da comparação "para a frente"
				// Note que não é uma troca: cópia é mais barato para o algoritmo (1 operação
				// contra 3).
				for (int posCopia = referencia; posCopia > (auxComp + 1); posCopia--) {
					this.vetorDados[posCopia] = this.vetorDados[posCopia - 1];
				}

				// inserimos o dado de referência em sua posição correta.
				this.vetorDados[auxComp + 1] = dadoRef;
			}
		}
	}
	
	
}
