package usecases;

import domain.*;

import java.util.List;

//classe que implementa a exclusão de veiculo
public class ExcluirVeiculoCtrl {
    private final VeiculoRepository repo;

    public ExcluirVeiculoCtrl(VeiculoRepository repo) {
        super();
        this.repo = repo;
    }

    public Resultado<Veiculo> excluirVeiculo(String placa )
    {
        try {
            if(Placa.create(placa)==null){
                return Resultado.erro(List.of(Erro.PLACA_INVALIDA));
            }
            Veiculo veiculo = repo.findByPlaca(placa);
            if(veiculo!=null){
                repo.remove(veiculo);
                return Resultado.ok(veiculo);
            }
            return Resultado.erro(List.of(Erro.CLIENTE_NAO_ENCONTRADO));

        } catch (Exception e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }
}
