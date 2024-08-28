package ui;

import domain.Erro;

import java.util.List;
import java.util.Scanner;

public class ExcluirVeiculoView {
    //recebe uma placa e exclui o veiculo

    public String readData(){
        var input = new Scanner(System.in);
        String placa;

        System.out.println("\n--------------------");
        System.out.println("Insira a placa do veículo que deseja excluir:");
        System.out.print("->");

        placa = input.next();

        return placa;
    }

    public void setErros(List<Erro> erros) {
        System.out.println("\nErro na exclusão:");
        for (var erro : erros) {
            switch (erro) {
                case PLACA_INVALIDA         -> System.out.println("- Placa inválida! Deve ter o formato AAA9999 onde AAA são letras (A-Z ou a-z) e 9999 são dígitos. ");
                case VEICULO_NAO_ENCONTRADO   -> System.out.println("- Veiculo não encontrado!");
                case ERRO_BD				-> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
            }
        }
        System.out.println();
    }

    /**
     * Imprime mensagem de sucesso
     */
    public void setSucesso() {
        System.out.println("\nExclusão bem-sucedida!");
    }

}
