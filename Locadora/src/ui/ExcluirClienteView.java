package ui;

import domain.Erro;

import java.util.List;
import java.util.Scanner;

import static domain.Erro.CLIENTE_NAO_ENCONTRADO;
import static domain.Erro.ERRO_BD;

public class ExcluirClienteView {
    /* Recebe um CPF e exclui o usuário */

    public Long readData(){
        var input = new Scanner(System.in);
        Long cpf;

        System.out.println("\n--------------------");
        System.out.println("Insira o CPF do usuário que deseja excluir (sem pontuação)");
        System.out.print("->");

        cpf = input.nextLong();
        
        return cpf;
    }

    public void mostraErro(List<Erro> erros){
        for (var erro : erros) {
            if (erro == CLIENTE_NAO_ENCONTRADO) System.out.println("- Cliente não encontrado!");
            else if( erro == ERRO_BD) System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
        }
    }

    public void mostraSucesso() {
        System.out.println("\n“Exclusão bem-sucedida!");
    }

}
