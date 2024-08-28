package usecases;

import domain.*;
import domain.dao.ListagemLocacaoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListarLocacaoCtrl {
    LocacaoRepository repo;
    ClienteRepository clienteRepository;
    VeiculoRepository veiculoRepository;

    public ListarLocacaoCtrl(LocacaoRepository repo, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository) {
        super();
        this.repo = repo;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public Resultado<List<ListagemLocacaoDTO>> recuperarTodasLocacoes() {
        try {
            //busca todas locacoes
            var listagemLocacoes = repo.findAll();

            List<ListagemLocacaoDTO> listagemResposta = new ArrayList<ListagemLocacaoDTO>();

            for (var locacao : listagemLocacoes)
            {
                ListagemLocacaoDTO item  = new ListagemLocacaoDTO();

                //busca nome do usuario no banco
                item.setCpf(locacao.getCpf());
                var cliente = clienteRepository.findByCPF(locacao.getCpf());
                item.setNome(cliente.getNome());

                //busca modelo carro no banco
                item.setPlaca(locacao.getPlaca());
                var veiculo = veiculoRepository.findByPlaca(locacao.getPlaca());
                item.setModelo(veiculo.getModelo());

                //coloca data locacao
                item.setDatalocacao(locacao.getDataLocacao());

                //adiciona na listagem de locacoes
                listagemResposta.add(item);
            }

            return Resultado.ok(listagemResposta);
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}
