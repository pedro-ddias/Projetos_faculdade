package Hash;

import Arvore.HeapMinExercicios;
import Dados.Atleta;
import Dados.Exercicio;

import Elemento.HeapElement;
import Lista.AtletaLista;
import Lista.HeapLista;

/**
 * Tabela Hash de exercícios
 */
public class HashExercicio {
    public static HeapLista tabela[]; // Criação de tabela do tipo Listas de Heap
    private static final int TAMANHO = 10000;

    public HashExercicio() {
        tabela = new HeapLista[TAMANHO];// Criação de listas
        inicializaListas();
    }

    public static void inicializaListas() {
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = new HeapLista();
        }
    }

    /**
     * Calculo de HASH
     */
    public static int funcaoHash(String cpf) {

        cpf = cpf.substring(0, 7); // Seleciona somente os 8 primeiros dígitos
        int cpfConvertido = Integer.parseInt(cpf);
        int posicao = cpfConvertido % 9973;
        return posicao;
    }

    public void addTabela(Exercicio exercicio) {
        int index = funcaoHash(exercicio.cpf);
        tabela[index].inserir(exercicio);
    }

    /**
     * Busca a Heap dentro da Hash e dentro da Lista
     * 
     * @param cpf
     * @return
     */
    public static HeapMinExercicios buscaHeapExercicio(String cpf) {
        HeapElement aux = tabela[funcaoHash(cpf)].primeiro.proximo;

        while (aux != null) {
            if (aux.exercicioHeap.get(0).equals(cpf)) {
                return aux.exercicioHeap;
            } else {
                aux = aux.proximo;
            }
        }
        return tabela[funcaoHash(cpf)].primeiro.exercicioHeap;
    }

    /**
     * Lista todos os exercicios do usuário em ordem crescente de data
     * 
     * @param exercicioHeap
     */
    public static void listaExercicio(HeapMinExercicios exercicioHeap) {
        int cont = 0;
        while (exercicioHeap.size() != 0) {
            int size = exercicioHeap.size();

            for (int i = size - 1; i >= 0; i--) {
                Exercicio aux = exercicioHeap.retono();
                System.out.println(cont + " - Data: " + aux.data + " - Nível de dificuldade: " + aux.dificuldade);
                cont++;
            }
        }
    }

    /**
     * Calculo total de calorias gastas em todos os exercicios a partir do nivel de
     * dificuldade do exercicio
     * 
     * @param exercicioHeap
     * @param atletaHash
     */
    public static void caloriasGastas(HeapMinExercicios exercicioHeap, HashAtleta atletaHash) {

        double kcal = 0;
        int series = 0;
        int repeticoes = 0;
        double tempExecucao = 0;
        int intervalo = 0;
        double tempo = 0;
        int alturaInt = 0;
        double pesoDouble = 0;
        int idade = 0;
        String genero = "";
        String altura = "";
        String peso = "";
        double tmb = 0;
        Exercicio aux = exercicioHeap.get(0);
        AtletaLista lista = HashAtleta.buscaAtleta(aux.cpf);
        Atleta atleta = lista.primeiro.proximo.atleta;
        altura = atleta.altura;
        altura = altura.replace(",", "");
        altura = altura.replace(".", "");
        alturaInt = Integer.parseInt(altura);
        peso = atleta.peso;
        peso = peso.replace(",", ".");
        pesoDouble = Double.parseDouble(peso);
        String data = atleta.dataNascimento;
        String auxData = data.substring(6);// Seleciona apenas o ano de nascimento
        int dataInt = Integer.parseInt(auxData);
        int dataAtual = 2020;

        idade = dataAtual - dataInt;// Calcula a idade do indivíduo apenas pelo ano
        genero = atleta.genero;

        if (genero.equals("M"))
            tmb = 66.5 + (5 * alturaInt) + (13.8 * pesoDouble) - (6.8 * idade); // Cálculo de taxa metabólica basal
                                                                                // masculino
        else if (genero.equals("F"))
            tmb = 655.1 + (1.8 * alturaInt) + (9.5 * pesoDouble) - (4.7 * idade); // Cálculo de taxa metabólica basal
                                                                                  // feminino

        while (exercicioHeap.size() != 0) {
            int size = exercicioHeap.size();

            for (int i = size - 1; i >= 0; i--) {
                aux = exercicioHeap.retono();

                switch (aux.dificuldade) { // A partir da dificuldade, define-se as variaveis
                    case "1":
                        series = 3;
                        repeticoes = 6;
                        tempExecucao = 2;
                        intervalo = 60;
                        break;
                    case "2":
                        series = 4;
                        repeticoes = 8;
                        tempExecucao = 2;
                        intervalo = 60;
                        break;
                    case "3":
                        series = 5;
                        repeticoes = 10;
                        tempExecucao = 2;
                        intervalo = 45;
                        break;
                    case "4":
                        series = 5;
                        repeticoes = 13;
                        tempExecucao = 3;
                        intervalo = 30;
                        break;
                    case "5":
                        series = 6;
                        repeticoes = 15;
                        tempExecucao = 5;
                        intervalo = 15;
                        break;
                }
                tempo = ((series * repeticoes * tempExecucao) + (series * intervalo)) / 60;
                kcal = kcal + (tempo * 6);
            }
            System.out.println("Sua taxa metabólica basal é: " + tmb + "kcal diárias");
            System.out.println("Você já gastou " + kcal + "kcal no total com nossos exercícios xD");
        }

    }

}
