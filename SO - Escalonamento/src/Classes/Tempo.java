package Classes;

public class Tempo{
	private int hora;
	private int minuto;
	private double segundo;
	
	/**
	 * Cria um objeto Tempo, que armazenará o horário.
	 * @param hora - Hora de início. Minutos e segundos serão inicializados com valor 0.
	 */
	public Tempo(int hora) {
		this.hora = hora;
		this.minuto = 0;
		this.segundo = 0.0;
	}
	
	/**
	 * Método para somar segundos ao horário.
	 * @param segundos - Segundos para adicionar. Pode receber segundos fracionados (ex. 0.5 segundos).
	 */
	public synchronized void adicionarSegundos(double segundos) {
		double segundosTotal = this.segundo + segundos;
		this.segundo = segundosTotal % 60;
		
		int minutosTotal = this.minuto + (int)(segundosTotal / 60);
		this.minuto = minutosTotal;
		if(minutosTotal >= 60) {
			int horasTotal = this.hora + (minutosTotal / 60);
			this.hora = horasTotal;
			this.minuto = minutosTotal % 60;
		}
	}

	/**
	 * Método para verificar se o Tempo passou de meio dia.
	 * @return Retorna resultado boolean. True se passou, e false se não passou.
	 */
	public synchronized boolean passouMeioDia(){
		if(this.hora >= 12){
			return true;
		}
		return false;
	}

	/**
	 * Comparar o Prazo que foi passado como parâmetro, com o prazo que esta na classe tempo. 
	 * @param tempo - Objeto tempo.
	 * @return (1) se o pedido esta com prazo excedido. E (2) se o pedido esta no prazo.
	 */
	public synchronized int comparaPrazo(Tempo tempo) {
        int retornoTempoMaior = 0;

		if (this.hora > tempo.getHora()){
            retornoTempoMaior = 1;
        }
        if (this.hora < tempo.getHora()) {
            retornoTempoMaior = 2;
        }

        if (this.hora == tempo.getHora()) {
            if (this.minuto >= tempo.getMinuto()) {
                if (this.segundo >= tempo.getSegundo()) {
                    retornoTempoMaior = 1;
                } else {
                    if (this.segundo < getSegundo()) {
                        retornoTempoMaior = 2;
                    }
                }
            }
            if (this.minuto < tempo.getMinuto()) {
                retornoTempoMaior = 2;
            }
        }
        return retornoTempoMaior;
    }
	
	/**
	 * Método para adicionar ao Tempo outro objeto Tempo.
	 * @param tempo - Objeto Tempo para somar
	 */
	public synchronized void somaTempo(Tempo tempo) {
		this.adicionarSegundos(tempo.quantosSegundos());
	}
	
	/**
	 * Método para dividir o Tempo n vezes. Subtrai a hora que começa o processo, no caso 8h da manhã.
	 * @param divisor - Número de vezes a dividir (divisor)
	 */
	public synchronized void divideTempo(double divisor) {
		double totalSegundos = this.quantosSegundos();
		totalSegundos /= divisor;
		
		this.zerarTempo();
		this.adicionarSegundos(totalSegundos);
		this.subtraiHora(8);
	}
	
	/**
	 * Método interno para subtrair horas de um objeto Tempo.
	 * @param quantHora - Quantas horas para subtrair.
	 */
	private synchronized void subtraiHora(int quantHora) {
		this.hora -= quantHora;
		if(this.hora < 0) this.hora = 0;
	}
	
	/**
	 * Método interno para converter o Tempo em segundos.
	 * @return A quantidade de segundos no Tempo.
	 */
	private synchronized double quantosSegundos() {
		return (this.segundo + (this.minuto * 60) + (this.hora * 60 * 60));
	}
	
	/**
	 * Método interno para zerar um objeto Tempo.
	 */
	private synchronized void zerarTempo() {
		this.segundo = 0;
		this.minuto = 0;
		this.hora = 0;
	}

	/**
	 * Getters
	 */
	public synchronized int getHora() {
		return this.hora;
	}

	public synchronized int getMinuto() {
		return this.minuto;
	}

	public synchronized double getSegundo() {
		return this.segundo;
	}

	@Override
	public synchronized String toString() {
		return String.format("%02d", this.hora) + ":" + String.format("%02d", this.minuto) + ":" + String.format("%02d", (int)this.segundo);
	}
	
}
