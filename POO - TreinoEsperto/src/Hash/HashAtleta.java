package Hash;

import Lista.AtletaLista;
import Dados.Atleta;
import Elemento.AtletaElement;

public class HashAtleta {
    public static AtletaLista tabela[];
    private static final int TAMANHO = 5000;

    public HashAtleta() {
        tabela = new AtletaLista[TAMANHO];
        inicializaListas();
    }

    public static void inicializaListas() {
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = new AtletaLista();
        }
    }

    public static int funcaoHash(String cpf) {
        cpf = cpf.substring(0, 7); // Seleciona somente os 8 primeiros dígitos
        int cpfConvertido = Integer.parseInt(cpf);
        int posicao = cpfConvertido % 4999;
        return posicao;
    }

    public void addTabela(Atleta atleta) {
        tabela[funcaoHash(atleta.cpf)].inserir(atleta);
    }

    public static AtletaLista buscaAtleta(String cpf) {
        return tabela[funcaoHash(cpf)];
    }

    /**
     * Busca os atletas dentro da faixa de altura especificada
     * 
     * @param max
     * @param min
     */
    public static void alturaAtletas(int max, int min) {
        AtletaElement aux = null;
        String altura = "";
        String cpf = "";
        int quantidade = 0;
        int alturaInt = 0;

        for (int i = 0; i < TAMANHO; i++) { // Loop para percorrer toda a Hash
            aux = tabela[i].primeiro.proximo;
            while (aux != null) {
                altura = aux.atleta.altura;
                altura = altura.replace(",", "");
                altura = altura.replace(".", "");
                alturaInt = Integer.parseInt(altura);
                if ((alturaInt <= max) && (alturaInt >= min)) { // Testa se o atleta está dentro do padrão
                    cpf = cpf.concat(aux.atleta.nome + " - " + aux.atleta.altura);
                    cpf = cpf.concat("\n");
                    quantidade++;
                }
                aux = aux.proximo;
            }
        }
        System.out.println(quantidade + " - " + cpf);
    }
}