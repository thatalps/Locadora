package persistence;

import domain.dao.LocacaoDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por mapear os dados vindos do BD (ResultSet) para o DTO
 */
public class LocacaoMapper {

    public LocacaoDTO map(ResultSet rs) throws SQLException {

        var df = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

        return new LocacaoDTO(rs.getString("id"),
                                rs.getLong("cpf"),
                                rs.getString("placa"),
                                LocalDateTime.from(df.parse(rs.getString("datalocacao")))
        );
    }
}
