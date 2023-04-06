import java.util.*;

public class Empresa {

    private List<Gerente> listaGerente;
    private List<Diretor> listaDiretor;
    private List<Analista> listaAnalista;

    public Empresa() {
        listaGerente = new ArrayList<Gerente>();
        listaDiretor = new ArrayList<Diretor>();
        listaAnalista = new ArrayList<Analista>();
    }

    /**
     * Método que calcula o valor da folha salarial dos gerentes
     * @return valor da folha salarial dos gerentes
     */
    public double calculaFolhaGerente() {

        double somaSalarios = 0.0;
        for (Gerente gerente : listaGerente) {
            somaSalarios += gerente.calculaSalario();
        }
        return somaSalarios;
    }

    /**
     * Método que calcula o valor da folha salarial dos diretores
     * @return valor da folha salarial dos diretores
     */
    public double calculaFolhaDiretor() {
        double somaSalarios = 0.0;
        for (Diretor diretor : listaDiretor) {
            somaSalarios += diretor.calculaSalario();
        }
        return somaSalarios;
    }

    /**
     * Método que calcula o valor da folha salarial dos analistas
     * @return valor da folha salarial dos analistas
     */
    public double calculaFolhaAnalista() {

        double somaSalarios = 0.0;
        for (Analista analista : listaAnalista) {
            somaSalarios += analista.calculaSalario();
        }
        return somaSalarios;
    }

    /**
     * Método que calcula o valor da folha salarial da empresa inteira
     * @return valor da folha salarial da empresa
     */
    public double calculaFolhaTotal() {
        return (calculaFolhaGerente() + calculaFolhaDiretor() + calculaFolhaAnalista());
    }
    
    /**
     * Método que calcula o valor do salário de um funcionário específico
     * @param id_funcionario id do funcionário que deseja saber o salário
     * @return valor do salário de um dos funcionários
     */
    public double calculaSalarioFuncionario(int id_funcionario) {

        for (Diretor diretor : listaDiretor) {

            if (diretor.getId_funcionario() > id_funcionario) {
                break;
            } else if (diretor.getId_funcionario() == id_funcionario) {
                return diretor.calculaSalario();
            }
        }

        for (Gerente gerente : listaGerente) {
            if (gerente.getId_funcionario() > id_funcionario) {
                break;
            } else if (gerente.getId_funcionario() == id_funcionario) {
                return gerente.calculaSalario();
            }
        }

        for (Analista analista : listaAnalista) {
            if (analista.getId_funcionario() > id_funcionario) {
                break;
            } else if (analista.getId_funcionario() == id_funcionario) {
                return analista.calculaSalario();
            }
        }

        return 0.0;
    }

    /**
     * Método que adiciona um funcionário à lista de funcionários
     * @param tipoFuncionario 0 para Gerente; 1 para Diretor; 2 para Analista
     * @param funcionario Objeto do tipo Funcionário
     * @return true se foi possível adicionar; falso se não foi
     */
    public boolean adicionaFuncionario(int tipoFuncionario, Funcionario funcionario) {
        switch (tipoFuncionario) {
            case 0:
                this.listaGerente.add((Gerente) funcionario);
                break;

            case 1:
            	this.listaDiretor.add((Diretor) funcionario);
                break;

            case 2:
            	this.listaAnalista.add((Analista) funcionario);
                break;
            default:
                return false;
        }
        return true;
    }
}
