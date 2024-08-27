package usecases;

import domain.Erro;
import domain.VeiculoBuilder;
import domain.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;

public class CadastroVeiculoCtrl {

    private final VeiculoRepository repo;

    public CadastroVeiculoCtrl(VeiculoRepository repo) {
        super();
        this.repo = repo;
    }

    public List<Erro> cadastrarVeiculo(VeiculoRequest request) {

        try {
            // 1 - Tenta construir o veiculo usando o builder
            var resultado = new VeiculoBuilder()
                    .comPlaca(request.placa())
                    .comModelo(request.modelo())
                    .comAnoFabricacao(request.anoFabricacao())
                    .comValorDiaria(request.valorDiaria())
                    .comQuilometragem(request.quilometragem()).build();

            // 2 - Verifica se teve sucesso ou não
            if (resultado.sucesso()) {
                var veiculo = resultado.valor;

                // 3 - Verifica se tem outro veiculo com a mesma placa
                var outroVeiculo = repo.findByPlaca(veiculo.getPlaca());

                if (outroVeiculo != null) {
                    return List.of(Erro.VEICULO_JA_EXISTENTE);
                }

                // 4 - Salva o veiculo
                repo.add(veiculo);

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
