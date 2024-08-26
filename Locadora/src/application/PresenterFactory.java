package application;

import domain.ClienteRepository;
import persistence.ClienteDAO;
import ui.*;
import usecases.CadastroClienteCtrl;
import usecases.ExcluirClienteCtrl;
import usecases.ListarClientesCtrl;

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
		               LISTAR_CLIENTE };
	
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
				//a) Alterar a funcionalidade para, inicialmente, ler do usuário a ordenação desejada: C-CPF ou N
				//Nome.
				//b) A lista de clientes deve ser apresentada de acordo com essa ordenação.
				var repository = new ClienteRepository(new ClienteDAO());
				var view = new ListarClientesView();
				var controller = new ListarClientesCtrl(repository);

				return new ListarClientesPrt(view, controller);
			}
		};
		return null;
	}
}
