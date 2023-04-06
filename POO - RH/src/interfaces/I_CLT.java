package interfaces;

public interface I_CLT {

	/**
	 * Método para calcular o salário baseado nas regras de CLT
	 * @return salário total do funcionário
	 */
	double icalculaSalario();
	
	/**
	 * Método para calcular o valor do décimo terceiro do funcionário
	 * @return valor do décimo terceiro
	 */
	double icalculaDecimoTerceiro();
	
	/**
	 * Método para calcular o valor das férias do funcionário
	 * @return valor das férias
	 */
	double icalculaFerias();
	
	/**
	 * Método para calcular o imposto de cada funcionário
	 * @return imposto do funcionario
	 */
	double icalculaImposto();
}
