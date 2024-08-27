package ui;

import domain.Erro;

import java.util.List;
import java.util.Scanner;

public class CadastroVeiculoView {
    /**
     * Lê os dados para cadastramento do veiculo
     * @return DTO com os dados lidos
     */
    public VeiculoData readData() {
        var input = new Scanner(System.in);
        String  placa, modelo, anoFabricacao, valorDiaria, quilometragem;

        System.out.println("\n--------------------");
        System.out.println("Cadastro de Veículos");
        System.out.println("--------------------");

        System.out.print("Placa: ");
        placa = input.nextLine();

        System.out.print("Modelo: ");
        modelo = input.nextLine();

        System.out.print("Ano de fabricação (AAAA): ");
        anoFabricacao = input.nextLine();

        System.out.print("Valor da Diária: ");
        valorDiaria = input.nextLine();

        System.out.print("Quilometragem: ");
        quilometragem = input.nextLine();

        // Devolve os dados lidos para o presenter
        return new VeiculoData(placa, modelo, anoFabricacao, valorDiaria, quilometragem);
    }

    public void setErros(List<Erro> erros) {
        System.out.println("\nErro no cadastramento:");
        for (var erro : erros) {
            switch (erro) {
                case PLACA_INVALIDA         -> System.out.println("- Placa inválida! Deve ter o formato AAA9999 onde AAA são letras (A-Z ou a-z) e 9999 são dígitos. ");
                case MODELO_INVALIDO        -> System.out.println("- Modelo inválido. Deve ter de 3 a 30 caracteres.");
                case ANO_FABRICACAO_INVALIDO-> System.out.println("- Ano inválido! Deve ser depois de 2000 e antes do ano atual.");
                case VALOR_DIARIA_INVALIDO  -> System.out.println("- Valor diária deve ser maior que 0!");
                case QUILOMETRAGEM_INVALIDA -> System.out.println("- Quilometragem deve ser um numero inteiro maior que 0!");
                case VEICULO_JA_EXISTENTE   -> System.out.println("- Veiculo já cadastrado!");
                case ERRO_BD				-> System.out.println("- Erro inesperado. Tente novamente mais tarde ou procure o suporte.");
            }
        }
        System.out.println();
    }

    /**
     * Imprime mensagem de sucesso
     */
    public void setSucesso() {
        System.out.println("\nCadastramento realizado com sucesso!");
    }


}
