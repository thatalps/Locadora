package ui;

import usecases.ListarClientesCtrl;

public class ListarClientePrt implements Presenter {

	private ListarClientesView view;
	private ListarClientesCtrl controller;
	
	public ListarClientePrt(ListarClientesView view, ListarClientesCtrl controller) {
		super();
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void run() {
		var resultado = controller.recuperarTodosClientes();
		
		if (resultado.sucesso())
			view.mostrarClientes(resultado.valor);
		else
			view.mostrarErro();
	}
}





