package Dados;

public class Exercicio {
    public String cpf;
    public String data;
    public String dificuldade;

    public Exercicio(String cpf, String data, String dificuldade) {
        this.cpf = cpf;
        this.data = data;
        this.dificuldade = dificuldade;
    }

    @Override
    public String toString() {
        String aux = "Cpf: " + cpf + " - Data: " + data + " - Dificuldade: " + dificuldade;
        return aux;
    }

    @Override
    public boolean equals(Object obj) {
        String aux = (String) obj;
        return this.cpf.equals(aux);
    }
}
