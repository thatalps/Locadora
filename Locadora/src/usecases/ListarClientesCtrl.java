package usecases;

import java.sql.SQLException;
import java.util.List;

import domain.Cliente;
import domain.ClienteRepository;
import domain.Erro;
import domain.Resultado;

/**
 * Classe que implementa a funcionalidade de consulta de clientes
 */
public class ListarClientesCtrl {

	private final ClienteRepository repo;
	
	public ListarClientesCtrl(ClienteRepository repo) {
		super();
		this.repo = repo;
	}

	/**
	 * Recupera todos os clientes do cadastro
	 * 
	 * @return Lista de clientes ou erro de acesso ao BD
	 */
	public Resultado<List<Cliente>> recuperarTodosClientes() {
		try {
			var clientes = repo.findAll();
			
			return Resultado.ok(clientes);
		} catch (SQLException e) {
			// Se ocorrer alguma exceção no BD, avisa
			return Resultado.erro(List.of(Erro.ERRO_BD));
		}
	}
}
