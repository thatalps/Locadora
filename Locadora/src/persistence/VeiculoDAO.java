package persistence;

import domain.Veiculo;
import domain.dao.IVeiculoDAO;
import domain.dao.VeiculoDTO;

import java.sql.SQLException;

public class VeiculoDAO  implements IVeiculoDAO {

    @Override
    public VeiculoDTO findByPlaca(String placa) throws SQLException
    {
        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from veiculos where placa = ?")) {

            // Define a placa do comando
            stmt.setString(1, placa);

            // Executa o comando que retorna um ResultSet
            try (var rs = stmt.executeQuery()) {

                var mapper = new VeiculoMapper();

                // Se existe um registro, converte os dados
                // do ResultSet em DTO usando o mapper
                if (rs.next())
                    return mapper.map(rs);

                // Se não existe, retorna nulo
                return null;
            }
        }
    }

    @Override
    public void add(Veiculo veiculo) throws SQLException {

    }

    @Override
    public void insert(Veiculo veiculo) throws SQLException {
        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("insert into veiculos values (?, ?, ?, ?, ?, ?)")){


            stmt.setString(1,  veiculo.getId());
            stmt.setString(2,  veiculo.getPlaca());
            stmt.setString(3,  veiculo.getModelo());
            stmt.setInt(4,     veiculo.getAnoFabricacao());
            stmt.setDouble(5,  veiculo.getValorDiaria());
            stmt.setInt(6,     veiculo.getQuilometragem());

            // Executar o comando
            stmt.execute();

        }
    }

    @Override
    public void update(Veiculo veiculo) throws SQLException {
        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("update veiculos set placa=?, modelo=?, anofabricacao=?, valordiaria=?, quilometragem=? where id=?")){

            stmt.setString(1,  veiculo.getPlaca());
            stmt.setString(2,  veiculo.getModelo());
            stmt.setInt(3,     veiculo.getAnoFabricacao());
            stmt.setDouble(4,  veiculo.getValorDiaria());
            stmt.setInt(5,     veiculo.getQuilometragem());
            stmt.setString(6,     veiculo.getId());

            // Executar o comando
            stmt.execute();

        }
    }
}
