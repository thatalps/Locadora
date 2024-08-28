package ui;

import domain.Erro;
import usecases.LocacaoRequest;
import usecases.LocacaoVeiculosCtrl;

import java.util.List;

public class LocacaoVeiculosPrt implements  Presenter{
    private LocacaoVeiculosView view;
    private LocacaoVeiculosCtrl controller;

    public LocacaoVeiculosPrt(LocacaoVeiculosView view, LocacaoVeiculosCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        Long cpf;
        String placa;
        List<Erro> erros;

        do {
            // 1 - Lê os dados da view
            var data = view.readData();

            // 2 - Converte os dados
            try {placa = data.placa();}
            catch(Exception ex) {placa = null;}

            try {cpf= Long.valueOf(data.cpf());}
            catch(Exception ex) {cpf = null;}


            // 3 - Entrega os dados para o controller para processar o cadastro
            erros = controller.cadastrarLocacao(
                    new LocacaoRequest(cpf,placa));

            // 4 - Verificar o status do processamento
            if (erros != null)
                view.setErros(erros);
            else
                view.setSucesso();
        } while (erros != null);

    }
}
