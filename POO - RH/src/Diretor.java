import interfaces.I_PJ;
import java.time.*;

public class Diretor extends Funcionario implements I_PJ {

    private String cnpj;
    private double salario_base;

    public Diretor(String nomeFuncionario, String cnpjFuncionario) {
        super(nomeFuncionario);
        this.cnpj = cnpjFuncionario;
    }


    @Override
    public double icalculaBonus() {
        Data dataHoje = new Data(this.admissao.retornaDiaAtual(), this.admissao.retornaMesAtual(), this.admissao.retornaAnoAtual());
        int diferenca = this.admissao.diferencaMeses(dataHoje);

        if(this.admissao.getMes() == dataHoje.retornaMesAtual() && this.admissao.getAno() == dataHoje.retornaAnoAtual()){
            return 0.0;
        }

        if (diferenca % 3 == 0 ) {
            return this.salario_base * 0.1;
        } else {
            return 0.0;
        }
    }

    @Override
    double calculaSalario() {
        return icalculaSalario();
    }

    @Override
    public double icalculaSalario() {
        return this.salario_base + icalculaBonus();
    }

    @Override
    public Data getAdmissao() {
        return super.getAdmissao();
    }

    @Override
    public void setAdmissao(Data admissao) {
        super.setAdmissao(admissao);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(double salario_base) {
        this.salario_base = salario_base;
    }
}
