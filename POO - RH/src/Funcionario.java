public abstract class Funcionario {
    private static int contFuncionario;
    protected String nome;
    private int id_funcionario;    
    protected Data admissao;

    static{
        contFuncionario = 0;
    }
    
    public Funcionario(String nomeFuncionario){
        this.nome = nomeFuncionario;
        this.id_funcionario = contFuncionario;
        contFuncionario++;
    }
    
    public int getId_funcionario() {
        return this.id_funcionario;
    }

    public Data getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Data admissao) {
        this.admissao = admissao;
    }

    /**
     * Método abstrato para calcular o salário de um funcionário
     * @return salário total de um funcionário
     */
    abstract double calculaSalario();
}
