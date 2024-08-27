package persistence;

import domain.dao.VeiculoDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por mapear os dados vindos do BD (ResultSet) para o DTO
 */
public class VeiculoMapper {
    /**
     * Mapeia os dados do ResultSet no DTO
     *
     * @param rs ResultSet com os dados
     * @return VeiculoDTO
     * @throws SQLException Exceção em caso de problemas no acesso ao BD
     */
    public VeiculoDTO map(ResultSet rs) throws SQLException {

        return new VeiculoDTO(rs.getString("id"),
                rs.getString("placa"),
                rs.getString("modelo"),
                rs.getInt("anofabricacao"),
                rs.getDouble("valordiaria"),
                rs.getInt("quilometragem"));
    }
}
