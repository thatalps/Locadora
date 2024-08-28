package persistence;

import domain.Locacao;
import domain.dao.ILocacaoDAO;
import domain.dao.LocacaoDTO;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class LocacaoDAO implements ILocacaoDAO {

    @Override
    public void update(Locacao locacao) throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("update locacoes set cpf=?, placa=?, datalocacao=? where id=?")) {

            // Define os valores dos parâmetros
            var df = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

            stmt.setLong(1,  locacao.getCpf());
            stmt.setString(2,  locacao.getPlaca());
            stmt.setString(3,  df.format(locacao.getDataLocacao()));
            stmt.setString(4,    locacao.getId());

            // Executa o comando
            stmt.execute();
        }
    }

    @Override
    public void insert(Locacao locacao) throws SQLException {
        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("insert into locacoes values (?, ?, ?, ?)")){


            var df = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

            stmt.setString(1,  locacao.getId());
            stmt.setLong(2,  locacao.getCpf());
            stmt.setString(3,  locacao.getPlaca());
            stmt.setString(4,     df.format(locacao.getDataLocacao()));

            // Executar o comando
            stmt.execute();

        }
    }

    @Override
    public LocacaoDTO findByCPFPlaca(long cpf, String placa) throws SQLException {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from locacoes where cpf = ? and placa = ?")) {

            // Define a placa do comando
            stmt.setLong(1, cpf);
            stmt.setString(2, placa);

            // Executa o comando que retorna um ResultSet
            try (var rs = stmt.executeQuery()) {

                var mapper = new LocacaoMapper();

                // Se existe um registro, converte os dados
                // do ResultSet em DTO usando o mapper
                if (rs.next())
                    return mapper.map(rs);

                // Se não existe, retorna nulo
                return null;
            }
        }
    }
}
