
import Arvore.ABB;
import Arvore.HeapMinExercicios;
import Cria.CriaAtleta;
import Cria.CriaExercicio;
import Cria.CriaSerie;

import Dados.Atleta;
import Lista.AtletaLista;

import Hash.HashAtleta;
import Hash.HashExercicio;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Arvore.HeapMinSeries;

public class App {

  public static void main(String[] args) throws FileNotFoundException {

    login();
  }

  public static void login() throws FileNotFoundException {
    Scanner ler = new Scanner(System.in);
    System.out.println("Loading..... ");
    HashAtleta atletaHash = CriaAtleta.criaAtleta(); // Criação da Hash e inserção
    HashExercicio exercicioHash = CriaExercicio.criaExercicio(); // Criação da Hash e inserção
    ABB seriesArvore = CriaSerie.criaSerie(); // Criação da ABB e inserção
    System.out.println("Bem vindo ao Treino Esperto!");
    System.out.println("Para fazer o login digite o CPF: ");
    String cpf = ler.nextLine();

    HeapMinSeries seriesHeap = CriaSerie.seriesHeap(seriesArvore, cpf); // Criação da Heap de Series

    AtletaLista buscaAtletaLista = HashAtleta.buscaAtleta(cpf); // Busca a lista do Atleta na Hash

    Atleta buscaAtleta = null;
    while (buscaAtleta == null) {

      buscaAtleta = buscaAtletaLista.localizarAtleta(cpf); // Busca o Atleta na lista

      if (buscaAtleta != null) {
        System.out.println("\nBem vindo " + buscaAtleta.nome + "!!");

        menuPrincipal(cpf, seriesArvore, seriesHeap, atletaHash, exercicioHash);
      } else {
        System.out.println("Atleta não encontrado, por favor digite seu cpf novamente: ");
        cpf = ler.nextLine();
        buscaAtletaLista = HashAtleta.buscaAtleta(cpf);
      }
    }
    ler.close();
  }

  public static void menuPrincipal(String cpf, ABB seriesArvore, HeapMinSeries seriesHeap, HashAtleta atletaHash,
      HashExercicio exercicioHash) throws FileNotFoundException {
    Scanner ler = new Scanner(System.in);
    int escolha = 0;
    while (escolha != 9) {
      System.out.println(" ");
      System.out.println("Escolha o número respectivo à funcionalidade desejada:");
      System.out.println("1 - Listar série");
      System.out.println("2 - Listar Exercícios");
      System.out.println("3 - Cálculo de gasto calórico");
      System.out.println("4 - Atletas por altura");
      System.out.println("9 - Finalizar o programa");

      escolha = ler.nextInt();

      switch (escolha) {
        case 1:
          CriaSerie.listaSerie(seriesHeap); // Método para listar as séries
          break;

        case 2:
          HeapMinExercicios buscaExercicioHeap = HashExercicio.buscaHeapExercicio(cpf);
          HashExercicio.listaExercicio(buscaExercicioHeap);

          break;
        case 3:
          buscaExercicioHeap = HashExercicio.buscaHeapExercicio(cpf);
          HashExercicio.caloriasGastas(buscaExercicioHeap, atletaHash);
          break;

        case 4:
          String max;
          String min;
          int maximo;
          int minimo;
          boolean condicao = true;

          do {
            System.out.println("Insira a altura máxima");
            max = ler.next();

            System.out.println("Insira a altura mínima");
            min = ler.next();

            if (max.contains(",")) {
              max = max.replace(",", "");
            }
            if (max.contains(".")) {
              max = max.replace(".", "");
            }
            if (min.contains(",")) {
              min = min.replace(",", "");
            }
            if (min.contains(".")) {
              min = min.replace(".", "");
            }
            maximo = Integer.parseInt(max);
            minimo = Integer.parseInt(min);
            if (minimo > maximo) {
              System.out.println("Faixa de altura inválida! Favor inserir novamente.");
              condicao = false;
            } else
              condicao = true;
          } while (condicao != true);

          HashAtleta.alturaAtletas(maximo, minimo);
          break;

        case 9:
          System.out.println("Volte sempre!");
          break;

        default:
          System.out.println("Funcionalidade escolhida não existe");
          break;
      }
    }
    ler.close();
  }
}
