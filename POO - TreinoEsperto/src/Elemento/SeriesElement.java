package Elemento;

import Dados.Series;

public class SeriesElement {
  public Series series;
  public SeriesElement proximo;

  // Construtor para o elemento
  public SeriesElement(Series series) {
    this.series = series;
    this.proximo = null;
  }

  @Override
  public boolean equals(Object obj){
    String aux = (String)obj;
    return series.equals(aux);
  }
}