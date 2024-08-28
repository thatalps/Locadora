package ui;

import domain.Resultado;
import domain.Veiculo;
import usecases.ListarVeiculosCtrl;

import java.util.List;

public class ListarVeiculosPrt implements Presenter{

    ListarVeiculosView view;
    ListarVeiculosCtrl controller;
    public ListarVeiculosPrt(ListarVeiculosView view, ListarVeiculosCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        char ordenacao = view.readData();
        Resultado<List<Veiculo>> resultado = null;

        ordenacao = Character.toUpperCase(ordenacao);
        switch (ordenacao)
        {
            case 'P'-> {
                resultado = controller.recuperarTodosVeiculosPorPlaca();
            }
            case 'M' -> {
                resultado = controller.recuperarTodosVeiculosPorModelo();
            }
            default -> System.out.println("Opção inválida!");
        }

        if (resultado.falha())
            view.mostrarErro();
        else
            view.mostrarVeiculos(resultado.valor);
    }
}
