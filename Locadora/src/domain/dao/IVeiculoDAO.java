package domain.dao;

import domain.Veiculo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface que representa as possíveis operações no BD
 */
public interface IVeiculoDAO {


    List<VeiculoDTO> findAllOrderByPlaca() throws SQLException;

    List<VeiculoDTO> findAllOrderByModelo() throws SQLException;

    VeiculoDTO findByPlaca(String placa) throws SQLException;


    void insert(Veiculo veiculo) throws SQLException;

    void update(Veiculo veiculo) throws SQLException;

    void delete(Veiculo veiculo) throws SQLException;
}
