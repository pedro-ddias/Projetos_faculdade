import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

    	Diretor diretor = new Diretor("Pedro", "3380504");
        diretor.setSalario_base(10000);
        diretor.setAdmissao(new Data(17,3,2018));
        System.out.println(diretor.calculaSalario());
        System.out.println();


    }
}

