package ui;


import domain.Resultado;
import usecases.ExcluirClienteCtrl;

public class ExcluirClientesPrt implements Presenter {

    private ExcluirClienteView view;
    private ExcluirClienteCtrl controller;

    public ExcluirClientesPrt(ExcluirClienteView view, ExcluirClienteCtrl controller) {
        super();
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void run(){
        long cpf = view.readData();
        Resultado resultado = controller.excluirCliente(cpf);

        if (resultado.sucesso())
            view.mostraSucesso();
        else
            view.mostraErro(resultado.erros);
    }

}
