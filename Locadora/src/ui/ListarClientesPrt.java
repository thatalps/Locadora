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

		ordenacao = Character.toUpperCase(ordenacao);
		switch (ordenacao)
		{
			case 'C'-> {
				resultado = controller.recuperarClientesOrdernadosPorCPF();
			}
			case 'N' -> {
				resultado = controller.recuperarClientesOrdernadosPorNome();
			}
			default -> System.out.println("Opção inválida!");
		}

		if (resultado.falha())
			view.mostrarErro();
		else
			view.mostrarClientes(resultado.valor);
	}
}





