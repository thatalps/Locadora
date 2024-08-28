package persistence;

import domain.Veiculo;
import domain.dao.IVeiculoDAO;
import domain.dao.VeiculoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO  implements IVeiculoDAO {

    @Override
    public List<VeiculoDTO> findAllOrderByModelo() throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        // Executa o comando que retorna um ResultSet
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from veiculos order by modelo asc")) {

            var mapper = new VeiculoMapper();
            var veiculos = new ArrayList<VeiculoDTO>();

            // Para todos os regitros vindoos do BD, converte os dados
            // do ResultSet em DTO usando o mapper
            while (rs.next())
                veiculos.add(mapper.map(rs));

            return veiculos;
        }
    }
    @Override
    public List<VeiculoDTO> findAllOrderByPlaca() throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        // Executa o comando que retorna um ResultSet
        try (var conn = DBConnection.get();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery("select * from veiculos order by placa asc")) {

            var mapper = new VeiculoMapper();
            var veiculos = new ArrayList<VeiculoDTO>();

            // Para todos os regitros vindoos do BD, converte os dados
            // do ResultSet em DTO usando o mapper
            while (rs.next())
                veiculos.add(mapper.map(rs));

            return veiculos;
        }
    }

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

    @Override
    public void delete(Veiculo veiculo) throws SQLException  {

        // Abre uma conexão com o BD
        // Cria um statement
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("delete from veiculos where id=?")) {

            // Define ID do comando
            stmt.setString(1, veiculo.getId());

            // Executa o comando
            stmt.execute();
        }
    }
}
