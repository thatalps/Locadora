package application;

import domain.ClienteRepository;
import domain.VeiculoRepository;
import persistence.ClienteDAO;
import persistence.VeiculoDAO;
import ui.*;
import usecases.*;

/**
 * Classe responsável por criar os presenters e sua estrutura
 */
public class PresenterFactory {

	/**
	 * Tipo do presenter
	 */
	public enum Type { MENU, 
		               CADASTRAR_CLIENTE, 
		               EXCLUIR_CLIENTE, 
		               LISTAR_CLIENTE,
		               CADASTRAR_VEICULO,
		               EXCLUIR_VEICULO
	};
	
    /**
     * Cria um presenter de acordo com o tipo solicitado
     * 
     * @param type Tipo do presenter
     * @return Presenter
     */
	public static Presenter get(Type type) {
		switch(type) {
			case MENU -> {
				var view = new MenuView();
				
				return new MenuPresenter(view);
			}
		
			case CADASTRAR_CLIENTE -> {
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new CadastroClienteView();
				var controller = new CadastroClienteCtrl(repository);

				return new CadastroClientePrt(view, controller);

			}
		
			case EXCLUIR_CLIENTE -> {
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new ExcluirClienteView();
				var controller = new ExcluirClienteCtrl(repository);

				return new ExcluirClientesPrt(view, controller);
			}
		
			case LISTAR_CLIENTE -> {
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new ListarClientesView();
				var controller = new ListarClientesCtrl(repository);

				return new ListarClientesPrt(view, controller);
			}
			case CADASTRAR_VEICULO -> {
				var repository = new VeiculoRepository(new VeiculoDAO());
				var view = new CadastroVeiculoView();
				var controller = new CadastroVeiculoCtrl(repository);

				return new CadastroVeiculoPrt(view, controller);
			}
			case EXCLUIR_VEICULO -> {
				var repository = new VeiculoRepository(new VeiculoDAO());
				var view = new ExcluirVeiculoView();
				var controller = new ExcluirVeiculoCtrl(repository);

				return new ExcluirVeiculoPrt(view, controller);
			}
		};
		return null;
	}
}
