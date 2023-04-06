import java.util.*;
import java.text.*;

public class Data {
	private int dia;
	private int mes;
	private int ano;
	private boolean bissexto;
	private static Data maior;
	
	/**
	 * Inicia a Data est�tica no dia 1/1/1900
	 */
	static {
		maior = new Data(1,1,1900,bissexto(1900));
	}
	
	/**
	 * Cria uma data um dia posterior � mais recente j� criada.
	 */
	public Data() {
		this.dia = maior.dia;
		this.mes = maior.mes;
		this.ano = maior.ano;
		this.bissexto = bissexto(this.ano);
		
		maior = this.somarDias(1);
	}
	
	/**
	 * Cria um objeto Data com tr�s par�metros.
	 * @param dia Dia da data. Caso seja inv�lido, � atribu�do o valor "1"
	 * @param mes M�s da data. Caso seja inv�lido, � atribu�do o valor "1"
	 * @param ano Ano da data. Caso seja inv�lido, � atribu�do o valor "1900"
	 */
	public Data(int dia, int mes, int ano) {
		if(ano >= 1900) {
			this.ano = ano;
		}
		else {
			this.ano = 1900;
		}
		
		this.bissexto = bissexto(ano);
		
		if(mes >= 1 && mes <= 12) {
			this.mes = mes;
		}
		else {
			this.mes = 1;
		}
		
		if(verificarDiaDoMes(dia, mes, ano)) {
			this.dia = dia;
		}
		else {
			this.dia = 1;
		}
		
		if(this.maisRecente(maior) == 1) { //Se for mair recente que a Data est�tica, substitui ela
			maior = this.somarDias(1);
		}
		
	}
	
	/**
	 * Esse construtor eu fiz porque estava dando loop infinito quando somava dias e verificava se a data
	 * era maior que a est�tica (a� somava mais um dia, criava outro objeto...), ent�o fiz isso pra funcionar
	 * @param dia Dia da data.
	 * @param mes M�s da data.
	 * @param ano Ano da data.
	 * @param bissexto Ano � bissexto ou n�o. (Esse par�metro eu s� usei pra conseguir fazer outro construtor)
	 */
	private Data(int dia, int mes, int ano, boolean bissexto) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.bissexto = bissexto;
	}
	
	/**
	 * M�todo para verificar se o dia � v�lido.
	 * @param dia Dia a ser validado
	 * @param mes M�s referente
	 * @param ano Ano referente
	 * @return True se for v�lido, false se for inv�lido
	 */
	private boolean verificarDiaDoMes(int dia, int mes, int ano) {
		if(mes == 2) {
			if(bissexto(ano)) {
				if(dia >= 1 && dia <= 29) {
					return true;
				}
			}
			else if(dia >= 1 && dia <= 28) {
				return true;
			}
		}
		
		else if((mes <= 7 && (mes % 2) == 1) || (mes > 7 && (mes % 2) == 0)) { //Conta pra saber os meses de 31 dias
			if(dia >= 1 && dia <= 31) {
				return true;
			}
		}
		
		else if(dia >= 1 && dia <= 30) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * M�todo para verificar se o ano � bissexto.
	 * @param ano Ano que quer verificar
	 * @return true se for bissexto, false se n�o for bissexto
	 */
	private static boolean bissexto(int ano) {
		boolean bissexto = false;

		if((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
			bissexto = true;
		}
		
		return bissexto;
	}
	
	/**
	 * M�todo para somar dias a uma data j� existente.
	 * @param dias Quantidade de dias que quer somar.
	 * @return Objeto Data com a nova data.
	 */
	public Data somarDias(int dias) {
		int auxDia = this.dia;
		int auxMes = this.mes;
		int auxAno = this.ano;
		
		for(int i = dias; i > 0; i--) {
			auxDia++;
			
			if(!verificarDiaDoMes(auxDia, auxMes, auxAno)) {
				auxDia = 1;
				auxMes++;
				
				if(auxMes > 12) {
					auxMes = 1;
					auxAno++;
				}
			}
		}
		
		Data novaData = new Data(auxDia, auxMes, auxAno, bissexto(auxAno));
		
		/**
		 * Complemento do "recurso t�cnico" kkkk
		 * Soma um dia e j� atribui na Data est�tica, pra n�o voltar pro m�todo de somar e ficar no loop infinito
		 */
		if(novaData.maisRecente(maior) == 1) {
			auxDia++;
			
			if(!verificarDiaDoMes(auxDia, auxMes, auxAno)) {
				auxDia = 1;
				auxMes++;
				
				if(auxMes > 12) {
					auxMes = 1;
					auxAno++;
				}
			}
			
			maior.dia = auxDia;
			maior.mes = auxMes;
			maior.ano = auxAno;
			maior.bissexto = bissexto(auxAno);
		}
		
		return novaData;
	}
	
	/**
	 * M�todo para verificar qual data � mais recente (considerando que estamos num futuro distante)
	 * @param outraData Um objeto Data para ser comparado
	 * @return 0 se forem iguais, 1 se a que chamou o m�todo for mais recente e 2 se a que foi passada for mais recente.
	 */
	public int maisRecente(Data outraData) {
		if(this.ano == outraData.ano) {
			if(this.mes == outraData.mes) {
				if(this.dia == outraData.dia) {
					return 0;
				}
				else if(this.dia > outraData.dia) {
					return 1;
				}
			}
			else if(this.mes > outraData.mes) {
				return 1;
			}
		}
		else if(this.ano > outraData.ano) {
			return 1;
		}	
		return 2;
	}

	
	public int mesesEsseAno(Data novaData) {
		return novaData.mes;
	}

	public int anosEsseAno(Data novaData) {
		return novaData.ano;
	}

	public int diasEsseAno(Data novaData) {
		return novaData.dia;
	}
	
	/**
	 * M�todo para mostrar a data completa.
	 */
	public void getData() {
		System.out.printf("%d/%d/%d\n", this.dia, this.mes, this.ano);
	}


	/**
	 * Retorna o Mês atual
	 */
	public int retornaMesAtual(){
		DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
		return Integer.parseInt(dateFormat.format(date));
	}

	public int retornaAnoAtual(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
		return Integer.parseInt(dateFormat.format(date));
	}

	public int retornaDiaAtual(){
		DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
		return Integer.parseInt(dateFormat.format(date));
	}


	public int diferencaMeses(Data outraData){
		int auxMes = this.mes;
		int auxAno = this.ano;
		int auxQuantMeses = 0;
		while(auxMes != outraData.mes || auxAno != outraData.ano){
			auxMes++;
			if(auxMes > 12){
				auxAno++;
				auxMes = 1;
			}
			auxQuantMeses++;
		}
		return auxQuantMeses;
	}

	public int getAno() {
		return ano;
	}
	
	public int getMes() {
		return mes;
	}
}
