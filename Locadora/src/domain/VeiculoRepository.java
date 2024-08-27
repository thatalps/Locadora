package domain;

import domain.dao.IVeiculoDAO;
import domain.dao.VeiculoDTO;

import java.sql.SQLException;
import java.util.UUID;


public class VeiculoRepository implements Repository{

    private final IVeiculoDAO dao;

    public VeiculoRepository(IVeiculoDAO dao) {
        this.dao = dao;
    }

    public Veiculo findByPlaca(String placa) throws SQLException  {

        var dto = dao.findByPlaca(placa);

        if (dto != null)
            return create(dto);

        // Se não existe, retorna nulo
        return null;
    }

    private Veiculo create(VeiculoDTO dto) {
        var resultado = new VeiculoBuilder()
                .comPlaca(dto.placa())
                .comModelo(dto.modelo())
                .comAnoFabricacao(dto.anoFabricacao())
                .comValorDiaria(dto.valorDiaria())
                .comQuilometragem(dto.quilometragem())
                .build();

        // Assume que a criação foi bem sucedida,
        // pois os dados do BD devem estar consistentes
        var veiculo = resultado.valor;

        // Seta o ID do objeto, pois ele veio do BD
        veiculo.setId(dto.id());

        return veiculo;
    }

    public void add(Veiculo veiculo) throws SQLException {
        // Se o veiculo NÃO tem ID, então NÃO veio do BD
        if (veiculo.getId() == null) {
            // Cria um ID artificial baseado no UUID
            veiculo.setId(UUID.randomUUID().toString());
            // Insere o veiculo no BD
            dao.insert(veiculo);
        }
        else// veiculo já existe: atualiza no BD
            dao.update(veiculo);
    }

}
