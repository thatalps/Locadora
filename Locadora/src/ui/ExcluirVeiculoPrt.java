package ui;

import domain.Resultado;
import usecases.ExcluirVeiculoCtrl;

public class ExcluirVeiculoPrt implements Presenter {
    private ExcluirVeiculoView view;
    private ExcluirVeiculoCtrl controller;

    public ExcluirVeiculoPrt(ExcluirVeiculoView view, ExcluirVeiculoCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run(){
        String placa = view.readData();
        Resultado resultado = controller.excluirVeiculo(placa);

        if (resultado.sucesso())
            view.setSucesso();
        else
            view.setErros(resultado.erros);
    }
}
