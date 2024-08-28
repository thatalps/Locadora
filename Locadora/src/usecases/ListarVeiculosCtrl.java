package usecases;

import domain.*;

import java.sql.SQLException;
import java.util.List;

public class ListarVeiculosCtrl {
    VeiculoRepository repo;
    public ListarVeiculosCtrl(VeiculoRepository repo) {
        super();
        this.repo = repo;
    }

    public Resultado<List<Veiculo>> recuperarTodosVeiculosPorPlaca() {
        try {
            var veiculos = repo.findAllOrderByPlaca();

            return Resultado.ok(veiculos);
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }

    public Resultado<List<Veiculo>> recuperarTodosVeiculosPorModelo() {
        try {
            var veiculos = repo.findAllOrderByModelo();

            return Resultado.ok(veiculos);
        } catch (SQLException e) {
            // Se ocorrer alguma exceção no BD, avisa
            return Resultado.erro(List.of(Erro.ERRO_BD));
        }
    }

}
