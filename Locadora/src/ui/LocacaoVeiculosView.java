package ui;

import domain.Erro;

import java.util.List;
import java.util.Scanner;

public class LocacaoVeiculosView {

    public LocacaoData readData() {
        var input = new Scanner(System.in);
        String  cpf, placa;

        System.out.println("\n--------------------");
        System.out.println("Locação de Veículos");
        System.out.println("--------------------");

        System.out.print("CPF do cliente: ");
        cpf = input.nextLine();

        System.out.print("Placa do veiculo: ");
        placa = input.nextLine();

        // Devolve os dados lidos para o presenter
        return new LocacaoData(cpf, placa);
    }

    public void setErros(List<Erro> erros) {
        System.out.println("\nErro na locação:");
        for (var erro : erros) {
            switch (erro) {
                case PLACA_INVALIDA         -> System.out.println("- Placa inválida! Deve ter o formato AAA9999 onde AAA são letras (A-Z ou a-z) e 9999 são dígitos. ");
                case CPF_INVALIDO           -> System.out.println("- CPF inválido!");
                case CLIENTE_NAO_ENCONTRADO   -> System.out.println("- Cliente não encontrado!");
                case VEICULO_NAO_ENCONTRADO   -> System.out.println("- Veiculo não encontrado!");
                case LOCACAO_JA_EXISTENTE   -> System.out.println("- Locação já cadastrada com esse CPF nesse veículo!");
                case ERRO_BD				-> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
            }
        }
        System.out.println();
    }

    /**
     * Imprime mensagem de sucesso
     */
    public void setSucesso() {
        System.out.println("\nLocação realizada com sucesso!");
    }

}
