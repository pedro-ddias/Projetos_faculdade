package Classes;
import java.util.ArrayList;
import java.util.List;

public class SyncList {

    public List<Dados> myList;

    public SyncList(){
    	this.myList = new ArrayList<Dados>();
    }

    /**
     * Adiciona um dado na lista
     * @param i dado a ser adicionado
     */
    public synchronized void addToList(Dados i){
        this.myList.add(i);
    }

    /**
     * Remove um dado na posição desejada e o retorna
     * @param pos posição do dado a ser removido
     * @return dado que foi removido
     */
    public synchronized Dados removeAt(int pos){
         if(pos<this.myList.size())
             return this.myList.remove(pos);
         else 
             return null;
    }

    /**
     * Retorna o dado contido na posição desejada
     * @param pos posição do dado desejo
     * @return dado da posição informada
     */
    public synchronized Dados lookAt(int pos){
        if(pos<this.myList.size())
            return this.myList.get(pos);
        else 
            return null;
    }

    /**
     * Verifica o tamanho da lista
     * @return tamanho da lista
     */
    public synchronized int getSize(){
        return this.myList.size();
    }    
}
