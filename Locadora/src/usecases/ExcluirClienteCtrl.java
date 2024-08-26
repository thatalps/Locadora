package usecases;

import domain.Cliente;
import domain.ClienteRepository;
import domain.Erro;
import domain.Resultado;

import java.sql.SQLException;
import java.util.List;
import java.util.SortedMap;

/**
 * Classe que implementa a funcionalidade de exclusão de clientes
 */
public class ExcluirClienteCtrl {

    private final ClienteRepository repo;

    public ExcluirClienteCtrl(ClienteRepository repo) {
        super();
        this.repo = repo;
    }

    public Resultado<Cliente> excluirCliente(Long cpf)
    {
        try {
            Cliente cliente = repo.findByCPF(cpf);
            if(cliente!=null){
                repo.remove(cliente);
                return Resultado.ok(cliente);
            }
            return Resultado.erro(List.of(Erro.CLIENTE_NAO_ENCONTRADO));

        } catch (Exception e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}
