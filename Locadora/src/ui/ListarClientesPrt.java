package ui;

import domain.Cliente;
import domain.Resultado;
import usecases.ListarClientesCtrl;

import java.util.List;

public class ListarClientesPrt implements Presenter {

	private ListarClientesView view;
	private ListarClientesCtrl controller;
	
	public ListarClientesPrt(ListarClientesView view, ListarClientesCtrl controller) {
		super();
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void run() {
		char ordenacao = view.readData();
		Resultado<List<Cliente>> resultado = null;

		switch (ordenacao)
		{
			case 'C' -> {
				resultado = controller.recuperarClientesOrdernadosPorCPF();
			}
			case 'N' -> {
				resultado = controller.recuperarClientesOrdernadosPorNome();
			}
		}
		
		if (resultado.sucesso())
			view.mostrarClientes(resultado.valor);
		else
			view.mostrarErro();
	}
}





