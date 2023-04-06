import Classes.*;

public class App {

	public static void main(String[] args){
		
		Produtor prod = new Produtor();
		
		Escalonador escalonador = new Escalonador();
		Esteira esteira1 = new Esteira(prod, 1, escalonador);
		Esteira esteira2 = new Esteira(prod, 2, escalonador);

		System.out.println();

		try {
			esteira1.start();
			esteira2.start();
			
			esteira1.join();
			esteira2.join();
		} catch (Exception e) {

		}
		System.out.println("\nHorário de finalização: " + Esteira.getTempo());
		System.out.println("Pedidos antes do meio dia: " + Esteira.getAntesMeioDia());
		System.out.println("Pedidos atrasados: " + Esteira.getAtrasos());
		System.out.println("Containers trocados: " + Escalonador.getTrocas());
		System.out.println("\nRELATÓRIO DE CONTAINERS:\n");
		escalonador.relatorioContainers();
		
		System.out.println();

	}

}

