package Classes;

public class Esteira extends Thread{

	private SyncList vetorDados;
	private int id;
	private Dados pedidoAtual;
	private static int prox = 1;
	private static Tempo tempo = new Tempo(8);
	private static int atrasos = 0;
	private static int antesMeioDia = 0;
	private static int[] tipoProduto;
	private static int TAM_MAX_PACOTE = 5_000;
	private Escalonador escalonador;
	
	/**
	 * Cria um objeto Esteira, que carregará os pedidos e ordenará se for necessário
	 * @param prod objeto Produtor com os dados a serem executados
	 * @param id ID da esteira
	 * @param esc objeto Escalonador para administrar os containers
	 */
	public Esteira(Produtor prod, int id, Escalonador esc){
		tipoProduto = prod.tipoProduto;
		this.vetorDados = prod.vetorSyncList;
		this.id = id;
		this.escalonador = esc;
	}

	@Override
	public void run() {
		
		while(this.vetorDados.lookAt(0) != null){
			int quantPedido = pegarPedido();
			
			while(quantPedido > 0) { //Enquanto tiver pacote a ser executado no pedido atual
				int cont = 0;
				
				while(prox != this.id) { //Verifica se é a vez dessa esteira
					
					//Se não for, da um timeout de 1ms nela, para "esperar" a outra esteira executar
					//Sem isso tava travando no meio da execução
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					cont++; 
					
					//Se o contador chegar a 10, é provável que a outra esteira já tenha finalizado,
					//então continua a execução na mesma esteira
					if(cont > 10) { 
						prox = this.id;
						if(this.id == 1) tempo.adicionarSegundos(5.5);
					}
					
				}
				quantPedido = passarPacote(quantPedido);
			}
		}
	}
	
	/**
	 * Pega um pedido, caso já tenha chegado
	 * @return quantidade de pacotes que serão produzidos
	 */
	private synchronized int pegarPedido() {
		verificaMeioDia();
		verificaAtraso();
		
		if(this.pedidoAtual != null) System.out.println("[Esteira: " + id + " - Finalização: "+ tempo +"] → "+ pedidoAtual); //se for o primeiro não printa
		Dados aux;
		
		for(int i = 0; i < vetorDados.getSize(); i++) {
			aux = vetorDados.lookAt(i);//pega o proximo pedido pra ver se ja chegou
			int tamanhoTotal = tipoProduto[aux.getTipoProduto()] * aux.getPedido(0); //tamanho total do pedido
			if(tempo.comparaPrazo(aux.getChegada()) == 1) { //verifica se já chegou esse pedido
				this.pedidoAtual = this.vetorDados.removeAt(i);
				this.escalonador.verificarPrimeirosContainers(this.pedidoAtual.getTipoProduto(), tamanhoTotal);
				int quantPacotes = pedidoAtual.getPedido(0) / (TAM_MAX_PACOTE / tipoProduto[pedidoAtual.getTipoProduto()]);
				
				return quantPacotes;
			}
		}
		return 0;
	}

	/**
	 * Passa o tempo ao final da execução das esteiras
	 * @param quantPedido quantidade de pacotes do pedido
	 * @return quantidade que falta para completar o pedido
	 */
	private synchronized int passarPacote(int quantPedido) {
		quantPedido--;
		if(this.id == 1) {
			prox = 2;
			
		}
		if(this.id == 2) {
			prox = 1;
			tempo.adicionarSegundos(5.5);
		}
		return quantPedido;
	}
	
	/**
	 * Verifica se houve atraso no último pacote executado
	 */
	private synchronized void verificaAtraso() {
		if(this.pedidoAtual != null && tempo.comparaPrazo(this.pedidoAtual.getPrazo()) == 1) {
			atrasos++;
		}
	}
	
	/**
	 * Verifica se o pedido foi atendido antes do meio dia
	 */
	private synchronized void verificaMeioDia() {
		if(this.pedidoAtual != null && !tempo.passouMeioDia()) {
			antesMeioDia++;
		}
	}
	
	public synchronized static Tempo getTempo() {
		return tempo;
	}
	
	public synchronized static int getAtrasos() {
		return atrasos;
	}
	
	public synchronized static int getAntesMeioDia() {
		return antesMeioDia;
	}
	
	public synchronized static int getProx() {
		return antesMeioDia;
	}
	
	/**
	 * Muda para a próxima esteira
	 * @param a ID da próxima esteira
	 */
	public synchronized static void setProx(int a) {
		prox = a;
	}
}
