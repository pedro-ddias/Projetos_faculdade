package interfaces;

public interface I_PJ {

	/**
	 * Método para calcular o salário baseado nas regras de PJ
	 * @return salário total do funcionário
	 */
	double icalculaSalario();
	
	/**
	 * Método para calcular o Bônus baseado nas regras de PJ
	 * @return Bônus do funcionário
	 */
	double icalculaBonus();
}
