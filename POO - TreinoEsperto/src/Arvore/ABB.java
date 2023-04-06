package Arvore;

import Dados.Series;

public class ABB {
    public Series raiz;
    public ABB subArvoreEsq, subArvoreDir;

    public ABB() {
        this.raiz = null;
        this.subArvoreDir = this.subArvoreEsq = null;
    }

    public Series buscar(String cpf) {
        if (raiz.compareTo(cpf) == 0)
            return raiz;
        else {
            if (raiz.compareTo(cpf) > 0) {
                if (subArvoreEsq == null)
                    return null;
                else
                    return subArvoreEsq.buscar(cpf);
            } else {
                if (subArvoreDir == null)
                    return null;
                else
                    return subArvoreDir.buscar(cpf);
            }
        }
    }

    public void inserir(Series novo) {
        if (raiz == null) {
            raiz = novo;
        } else {
            if (novo.compareTo(raiz.toString()) < 0) {
                if (subArvoreEsq == null)
                    subArvoreEsq = new ABB();
                subArvoreEsq.inserir(novo);
            } else {
                if (subArvoreDir == null)
                    subArvoreDir = new ABB();
                subArvoreDir.inserir(novo);
            }
        }
    }

    public String emOrdem(String cpf, HeapMinSeries seriesHeap) {
        StringBuilder aux = new StringBuilder();

        if (subArvoreEsq != null) {
            if (subArvoreEsq.raiz.cpf.equals(cpf)) {
                seriesHeap.insere(subArvoreEsq.raiz);
                aux.append(subArvoreEsq.emOrdem(cpf, seriesHeap));
                aux.append("\n");
            } else
                subArvoreEsq.emOrdem(cpf, seriesHeap);
        }

        if (subArvoreDir != null) {
            if (subArvoreDir.raiz.cpf.equals(cpf)) {

                seriesHeap.insere(subArvoreDir.raiz);
                aux.append(subArvoreDir.emOrdem(cpf, seriesHeap));
                aux.append("\n");
            } else
                subArvoreDir.emOrdem(cpf, seriesHeap);
        }

        return aux.toString();
    }

}
