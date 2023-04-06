package Classes;

import java.util.*;

public class Escalonador{
	
	private Tempo tempo;
	private List<Container> containers;
	private static int quantTrocas = 0;

	/**
	 * Cria um escalonador, responsável pela alocação nos containers
	 */
	public Escalonador() {
		this.tempo = Esteira.getTempo();
		this.containers = new ArrayList<Container>();
	}
	
	/**
	 * Adiciona um container na lista
	 * @param tipoProduto tipo de produto do container
	 */
	public synchronized void adicionarContainer(int tipoProduto) {
		this.containers.add(new Container(tipoProduto));
	}
	
	/**
	 * Verifica se há um container disponível para o pedido entre os 4 mais próximos das esteiras
	 * @param tipoProduto tipo do produto a ser armazenado
	 * @param tamanhoNecessario tamanho total do pedido
	 * @return true se foi alocado
	 */
	public synchronized boolean verificarPrimeirosContainers(int tipoProduto, int tamanhoNecessario) {
		Container cont;
		for(int i = 0; i < 4; i++) {
			if(i < containers.size()) {
				cont = containers.get(i);
				if(cont.getProdutoContido() == tipoProduto) {
					if(cont.sobraEspaco(tamanhoNecessario)) {
						return true;
					}
				}
			}
			else { // Se não tiver pelo menos 4 containers, adiciona um container
				adicionarContainer(tipoProduto);
				containers.get(i).sobraEspaco(tamanhoNecessario);
				return true;
			}
		}
		verificarContainers(tipoProduto, tamanhoNecessario); // Caso não tenha container disponível entre os 4, manda procurar em todos
		return true;
	}
	
	/**
	 * Verifica todos os containers existentes para efetuar a troca
	 * @param tipoProduto tipoProduto tipo do produto a ser armazenado
	 * @param tamanhoNecessario tamanho total do pedido
	 * @return true se foi alocado
	 */
	public synchronized boolean verificarContainers(int tipoProduto, int tamanhoNecessario) {
		int i = 0;
		for(Container cont : containers) {
			if(cont.getProdutoContido() == tipoProduto) {
				if(cont.sobraEspaco(tamanhoNecessario)) {
					Collections.swap(containers, i, maisCheio()); // Troca o novo container pelo mais cheio dos 4 mais próximos
					quantTrocas++;
					this.tempo.adicionarSegundos(30);
					return true;
				}
			}
			i++;
		}
		
		//Caso não tenha, cria um container novo
		adicionarContainer(tipoProduto);
		containers.get(i).sobraEspaco(tamanhoNecessario);
		Collections.swap(containers, i, maisCheio());
		quantTrocas++;
		this.tempo.adicionarSegundos(30);
		
		return true;
	}
	
	/**
	 * Verifica qual o container mais cheio entre os 4 mais próximo das esteiras
	 * @return posicao do mais cheio na lista
	 */
	private synchronized int maisCheio() {
		Container maior = containers.get(0);
		int posMaior = 0;
		for(int i = 1; i < 4; i++) {
			if(containers.get(i).getEspacoOcupado() > maior.getEspacoOcupado()) {
				maior = containers.get(i);
				posMaior = i;
			}
		}
		return posMaior;
	}
	
	/**
	 * Gera um relatório dos containers
	 */
	public void relatorioContainers() {
		for(int i = 1; i <= 16; i++) {
			int quantContainer = 0;
			int ocupacao = 0;
			for(Container c : containers) {
				if(c.getProdutoContido() == i) {
					ocupacao += c.getEspacoOcupado();
					quantContainer++;
					//System.out.println("Produto "+i+" - escaço ocupado "+c.getEspacoOcupado());
				}
			}
			double media = ((double)ocupacao / (double)quantContainer) * (100d / Container.getCapacidade());
			System.out.printf("Produto " + i + " possui " + quantContainer + " containers e ocupacao média de %.1f%%\n", media);
		}
	}
	
	public static int getTrocas() {
		return quantTrocas;
	}

}
