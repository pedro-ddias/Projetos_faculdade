package Classes;

public class Container {
	
	private int produtoContido;
	private int espacoOcupado;
	private static final int CAPACIDADE = 1_000_000;

	public Container(int tipoProduto) {
		this.produtoContido = tipoProduto;
		this.espacoOcupado = 0;
	}
	
	/**
	 * Método para saber se está sobrando espaço no container atual
	 * @param tamanhoPedido tamanho a ser alocado no container
	 * @return true se possui espaço e false se não possui espaço
	 */
	public synchronized boolean sobraEspaco(int tamanhoPedido) {
		if((this.espacoOcupado + tamanhoPedido) <= CAPACIDADE) {
			alocarEspaco(tamanhoPedido);
			return true;
		}
		return false;
	}
	
	/**
	 * Método para alocar espaço em um container
	 * @param tamanhoPedido tamanho a ser alocado
	 */
	private synchronized void alocarEspaco(int tamanhoPedido) {
		this.espacoOcupado += tamanhoPedido;
	}
	
	public synchronized int getProdutoContido() {
		return this.produtoContido;
	}
	
	public synchronized int getEspacoOcupado() {
		return this.espacoOcupado;
	}
	
	public static int getCapacidade() {
		return CAPACIDADE;
	}

}
