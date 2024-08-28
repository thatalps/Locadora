package usecases;

import domain.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class LocacaoVeiculosCtrl {

    LocacaoRepository repo;
    ClienteRepository clienteRepository;
    VeiculoRepository veiculoRepository;


    public LocacaoVeiculosCtrl(LocacaoRepository repository, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository) {
        super();
        this.repo = repository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Erro> cadastrarLocacao(LocacaoRequest request) {

        try {
            // 1 - Tenta construir o veiculo usando o builder
            var resultado = new LocacaoBuilder()
                    .comCPF(request.cpf())
                    .comPlaca(request.placa())
                    .comDataLocacao(LocalDateTime.now())
                    .build();

            // 2 - Verifica se teve sucesso ou não
            if (resultado.sucesso()) {
                var locacao = resultado.valor;

                //verificar se cliente existe
                var clienteExiste = clienteRepository.findByCPF(request.cpf());
                if(clienteExiste==null)
                    return List.of(Erro.CLIENTE_NAO_ENCONTRADO);


                //verificar se veiculo existe
                var veiculoExiste = veiculoRepository.findByPlaca(request.placa());
                if(veiculoExiste==null)
                    return List.of(Erro.VEICULO_NAO_ENCONTRADO);

                // 3 - Verifica se tem outro veiculo com a mesma placa
                var outraLocacao = repo.findByCPFPlaca(locacao.getCpf(), locacao.getPlaca());

                if (outraLocacao != null) {
                    return List.of(Erro.LOCACAO_JA_EXISTENTE);
                }

                // 4 - Salva o veiculo
                repo.add(locacao);

                // Avisa que não tem erro
                return null;
            } else {
                // Retorna os códigos de erro
                return resultado.erros;
            }
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return List.of(Erro.ERRO_BD);
        }

    }

}
