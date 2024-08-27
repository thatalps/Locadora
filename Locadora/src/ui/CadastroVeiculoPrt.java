package ui;

import domain.Erro;
import usecases.CadastroVeiculoCtrl;
import usecases.VeiculoRequest;

import java.util.List;

public class CadastroVeiculoPrt implements Presenter{

    private CadastroVeiculoView view;
    private CadastroVeiculoCtrl controller;

    public CadastroVeiculoPrt(CadastroVeiculoView view, CadastroVeiculoCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run() {
        Double valorDiaria;
        Integer anoFabricacao, quilometragem;
        String placa, modelo;
        List<Erro> erros;

        do {
            // 1 - Lê os dados da view
            var data = view.readData();

            // 2 - Converte os dados
            try {placa = data.placa();}
            catch(Exception ex) {placa = null;}

            try {modelo= data.modelo();}
            catch(Exception ex) {modelo = null;}

            try {anoFabricacao= Integer.valueOf(data.anoFabricacao());}
            catch(Exception ex) {anoFabricacao = null;}

            try {valorDiaria= Double.valueOf(data.valorDiaria());}
            catch(Exception ex) {valorDiaria = null;}

            try {quilometragem= Integer.valueOf(data.quilometragem());}
            catch(Exception ex) {quilometragem = null;}

            // 3 - Entrega os dados para o controller para processar o cadastro
            erros = controller.cadastrarVeiculo(
                    new VeiculoRequest(placa,modelo,anoFabricacao,valorDiaria,quilometragem));

            // 4 - Verificar o status do processamento
            if (erros != null)
                view.setErros(erros);
            else
                view.setSucesso();
        } while (erros != null);
    }

}
