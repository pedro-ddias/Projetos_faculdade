package Classes;

public class Dados {
	private String cliente;
	private int[] pedido; 
	private Tempo tempoChegada;
	private Tempo prazo;
	private int tipo;
	
	/**
	 * Cria um objeto Dado, que armazena cada linha do arquivo.
	 * @param cliente nome do cliente
	 * @param produtos quantidade de produtos
	 * @param prazo prazo para empacotamento em minutos
	 * @param minutoChegada minuto de chegada do produto
	 * @param tipoProduto tipo do produto
	 */
	public Dados(String cliente, int produtos, int prazo, int minutoChegada, int tipoProduto) {

		Tempo tempoChegada = new Tempo(8);
		tempoChegada.adicionarSegundos(minutoChegada * 60);

		this.cliente = cliente;
		pedido = new int[3];
		this.pedido[0] = produtos;
		this.pedido[1] = (prazo * 60) + (minutoChegada * 60);
		this.pedido[2] = tipoProduto;
		this.tipo = tipoProduto;
		
		Tempo tempoPrazo = new Tempo(8);
		tempoPrazo.adicionarSegundos(this.pedido[1]);
		
		this.tempoChegada = tempoChegada;
		this.prazo = tempoPrazo;
	}
	
	
	/**
	 * Método que retorna a quantidade de produtos ou o prazo
	 * @param qual 0 para quantidade de produtos, 1 para o prazo e 2 para o tipo de produto.
	 * @return Retorna a quantidade de produtos ou prazo.
	 */
	public synchronized int getPedido(int qual) {
		return this.pedido[qual];
	}
	
	public synchronized Tempo getPrazo() {
		return this.prazo;
	}
	
	public synchronized Tempo getChegada() {
		return this.tempoChegada;
	}
	
	public synchronized int getTipoProduto() {
		return pedido[2];
	}
	
	@Override
	public synchronized String toString() {
		Tempo prazo = new Tempo(8);
		prazo.adicionarSegundos(pedido[1]);
		String tipo = Integer.toString(this.tipo);
		tipo = (tipo.length() > 1) ? "" + this.tipo : "0" + this.tipo;
		
		String prod = Integer.toString(pedido[0]);
		prod = (prod.length() > 2) ? prod+"" : prod+"  ";

		prod = (prod.length() > 3) ? prod : prod+" ";
		return "Produto " + tipo + " | Chegada: " + this.tempoChegada + " | Prazo = " + prazo + " | Produtos = " + prod + " | Cliente: " + this.cliente; //+ "" + this.cliente
	}
}
