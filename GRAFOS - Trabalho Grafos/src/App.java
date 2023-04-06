import Algoritmo.Kruskal;
import Cria.*;
import Padroes.*;
import java.util.*;

public class App {

    private static Grafo grafo = new Grafo();
    private final static Scanner ler = new Scanner(System.in);
    private static CriaMatriz matriz = new CriaMatriz();
    private static ArrayList<Cluster> clusters = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // Aqui é iniciado o menu com as opções.
        String arquivo = opcaoMenu();

        String[][] dissimilaridade = matriz.CriaMatriz();

        CriaVertice criaVertice = new CriaVertice();
        CriaAresta criaAresta = new CriaAresta();
        CriaAluno criaAluno = new CriaAluno();

        criaVertice.CriaVertice(grafo);
        criaAresta.CriaAresta(grafo, dissimilaridade);
        criaAluno.CriaAluno(grafo, arquivo);

        Kruskal kruskal = new Kruskal(grafo);

        System.out.println("Quantos serão os professores?");
        int numProf = ler.nextInt();

        // Aqui é realizado a separação dos dados.
        separaGrupos(kruskal, numProf);

        // separarGruposMaiores(kruskal, numProf);
        
        // Indentificando os grupos criados.
        int[] grupos = identificaGrupos(kruskal, numProf);

        // Adicionando os Clusters criados na lista de Clusters.
        adicionaClusterNaLista(kruskal, grupos);

        agrupaProfs(numProf);

        // Métoddo para exibir os Clusters da lista.
        exibeClusters();

        // System.out.println(kruskal.toString());
        System.out.println("fim");

        ler.close();
    }

    /**
     * Menu com opções de arquivo para escolha do usuário.
     * @return String
     */
    private static String opcaoMenu() {
        System.out.println("Selecione a quantidade desejada para a realização dos testes?");
        System.out.println("1 - 10 entradas \n2 - 30 entradas \n3 - 50 entradas \n4 - 100 entradas \n5 - 1000 entradas \n6 - 100000 entradas");
        int opcao = ler.nextInt();
        String arquivo = "";

        switch (opcao) {
            case 1:
                arquivo = "src/entrada10.txt";
                break;
        
            case 2:
                arquivo = "src/entrada30.txt";
                break;
            case 3:
                arquivo = "src/entrada50.txt";
                break;  
            case 4:
                arquivo = "src/entrada100.txt";
                break;
            case 5:
                arquivo = "src/entrada1000.txt";
                break;
            case 6:
                arquivo = "src/entrada100000.txt";
                break;
            default:
                System.out.println("Opção não encontrada!");
                break;
        }
        return arquivo;
    }

    /**6
     * Método que é chamado o algorítmo de Kruskal para realizar a separação dos Alunos. 
     * @param kruskal
     * @param numProf
     */
    private static void separaGrupos(Kruskal kruskal, int numProf) {
        int numGrupos = 19;

        if (numProf < 20) {
            numGrupos = numProf - 1;
        }

        for (int i = 0; i < numGrupos; i++) { // Quantidade de arestas que vão ser retiradas
            Vertice maior = kruskal.getVertices().get(0);

            for (int j = 1; j < kruskal.getVertices().size(); j++) {
                Vertice aux = kruskal.getVertices().get(j);
                if (maior.getListaArestas().size() < aux.getListaArestas().size())
                    maior = aux;
            }

            Aresta maiorAresta = maior.getListaArestas().get(0);
            for (int j = 1; j < maior.getListaArestas().size(); j++) {
                Aresta auxAresta = maior.getListaArestas().get(j);
                if (maiorAresta.getPeso() == auxAresta.getPeso()) {
                    maiorAresta.setGrauAresta();
                    auxAresta.setGrauAresta();
                    if (maiorAresta.getGrauAresta() < auxAresta.getGrauAresta())
                        maiorAresta = auxAresta;
                } else if (maiorAresta.getPeso() < auxAresta.getPeso()) {
                    maiorAresta = auxAresta;
                }
            }
            kruskal.separar(maiorAresta);
            // System.out.println(kruskal.toString());
        }
    }


    /**
     * Método para realizar a separação do grafo referente ao número de professores.
     * @param kruskal
     * @param numProf
     */
    private static void separarGruposMaiores(Kruskal kruskal, int numProf){

        // Separar conforme a quantidade de professores. (Ex. 5, buscar as 5 maiores arestas e remover).

        System.out.println(kruskal.toString());
        System.out.println("--------------------------------");

        // Aqui eu percorro minha lista de vertices para pegar o vertice de maior grau.
        List<Vertice> listaVertices = kruskal.getVertices();
        Vertice maiorVertice = listaVertices.get(0);

        for (int j = 1; j < listaVertices.size(); j++) {

            Vertice aux = listaVertices.get(j);
            if (maiorVertice.getListaArestas().size() < aux.getListaArestas().size())
                maiorVertice = aux;
        }

        // Aqui eu percorro minha lista de arestas para pegar a maior aresta.
        List<Aresta> listaArestas = maiorVertice.getListaArestas();
        Aresta maiorAresta = listaArestas.get(0);

        for (int i = 1; i < listaArestas.size(); i++) {
            Aresta auxAresta = listaArestas.get(i);

            if (maiorAresta.getPeso() < auxAresta.getPeso()){
                maiorAresta = auxAresta;
            }
        }
        kruskal.separar(maiorAresta);

       System.out.println(kruskal.toString());
    }

    /**
     * Método resposável por identificar os grupos. Retornando uma lista com os grupos.
     * @param kruskal
     * @param numProf
     * @return grupos
     */
    private static int[] identificaGrupos(Kruskal kruskal, int numProf) {

        int numGrupos = 20;

        if (numProf < 20) {
            numGrupos = numProf;
        }

        int[] grupos = new int[numGrupos];
        for (int i = 0; i < grupos.length; i++) {
            grupos[i] = -1;
        }
        int aux = 0;

        for (int i = 0; i < kruskal.getVertices().size(); i++) {
            int grupo = kruskal.getVertices().get(i).getGrupo();
            boolean existe = false;
            for (int j = 0; j < numGrupos; j++) {
                if (grupo == grupos[j])
                    existe = true;
            }
            if (existe == false) {
                grupos[aux] = grupo;
                aux++;
            }
            if (aux == numGrupos)
                break;
        }

        return grupos;
    }

    /**
     * Método para adicionar os Clusters criados na lista de Clusters.
     * @param kruskal
     * @param grupos
     */
    private static void adicionaClusterNaLista(Kruskal kruskal, int[] grupos){
        
        for (int i = 0; i < grupos.length; i++) {
            int grupo = grupos[i];
            Cluster cluster = new Cluster(kruskal.getGrafo(), grupo);
            if (!cluster.isEmpty())
                clusters.add(cluster);
        }
    }


    /**
     * Método para agrupar os professores.
     * @param numProf
     */
    public static void agrupaProfs(int numProf) {

        int contador = 0;

        for (Cluster cluster : clusters) {
            cluster.addProf(1);
        }

        if (numProf > 20) {
            int diferença = numProf - 20;
            for (int i = 0; i < diferença; i++) {
                Cluster maior = new Cluster();

                for (Cluster cluster : clusters) {
                    if (cluster.getAlunos() > maior.getAlunos())
                        maior = cluster;
                }
                maior.addProf(1);
            }
        }
    }

    /**
     * Método para exibir os Clusters.
     */
    private static void exibeClusters(){
        for(int i = 0; i < clusters.size(); i++){
            System.out.println(clusters.get(i).toString());
        }
    }
}
