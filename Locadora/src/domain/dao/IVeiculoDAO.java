package domain.dao;

import domain.Veiculo;

import java.sql.SQLException;

/**
 * Interface que representa as possíveis operações no BD
 */
public interface IVeiculoDAO {

    VeiculoDTO findByPlaca(String placa) throws SQLException;

    void add(Veiculo veiculo) throws SQLException;

    void insert(Veiculo veiculo) throws SQLException;

    void update(Veiculo veiculo) throws SQLException;

    void delete(Veiculo veiculo) throws SQLException;

}
