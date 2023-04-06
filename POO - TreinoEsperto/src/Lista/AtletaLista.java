package Lista;

import Dados.Atleta;
import Elemento.AtletaElement;

public class AtletaLista {
  public static Object atletaLista;
  public AtletaElement primeiro;
  public AtletaElement ultimo;
  public String nome;

  // Cria uma lista vazia com elemento sentinela
  public AtletaLista() {
    primeiro = new AtletaElement(null);
    ultimo = primeiro;
  }

  // Insere um atleta no fim da lista
  public void inserir(Atleta atleta) {
    AtletaElement novo = new AtletaElement(atleta);
    ultimo.proximo = novo;
    ultimo = novo;
  }

  // localizar um atleta pelo CPF
  public Atleta localizarAtleta(String cpf) {
    AtletaElement aux = primeiro.proximo;

    while (aux != null) {
      if (aux.equals(cpf)) {
        return aux.atleta;
      } else {
        aux = aux.proximo;
      }
    }

    return null;
  }
}
