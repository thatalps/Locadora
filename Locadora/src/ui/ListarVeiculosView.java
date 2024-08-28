package ui;

import domain.Veiculo;

import java.util.List;
import java.util.Scanner;

public class ListarVeiculosView {

    public char readData(){
        var input = new Scanner(System.in);
        char order;
        //Ler do usuário a ordenação desejada: P-Placa ou M-Modelo.
        System.out.println("\n--------------------");
        System.out.println("Deseja ordenar por: \nP - Placa \nou \nM - Modelo");
        System.out.print("->");

        order = input.next().charAt(0);

        return order;
    }

    public void mostrarVeiculos(List<Veiculo> veiculos) {

        if (veiculos.isEmpty())
            System.out.println("\nNão há veiculos cadastrados");
        else {
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Placa      Modelo                         Ano  Diaria                                            KM");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            for (var c: veiculos) {
                System.out.printf("%-10s %-30s %s %-50s (%d)\n",
                        c.getPlaca(),
                        c.getModelo(),
                        c.getAnoFabricacao(),
                        c.getValorDiaria(),
                        c.getQuilometragem());
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }

}
