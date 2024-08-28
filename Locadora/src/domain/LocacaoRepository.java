package domain;

import domain.dao.ILocacaoDAO;
import domain.dao.LocacaoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocacaoRepository implements Repository{
    private final ILocacaoDAO dao;

    public LocacaoRepository(ILocacaoDAO dao) {
        this.dao = dao;
    }

    public List<Locacao> findAll() throws SQLException  {
        // Busca todas as locaoes
        var dtos = dao.findAll();

        // Converte os DTOs vindo do repositório em clientes
        var locacaos = new ArrayList<Locacao>();

        for (var dto : dtos)
            locacaos.add(create(dto));

        return locacaos;
    }

    public Locacao findByCPFPlaca(long cpf, String placa) throws SQLException {

        var dto = dao.findByCPFPlaca(cpf, placa);

        if (dto != null)
            return create(dto);

        // Se não existe, retorna nulo
        return null;
    }

    public void add(Locacao locacao) throws SQLException {
        // Se o locacao NÃO tem ID, então NÃO veio do BD
        if (locacao.getId() == null) {
            // Cria um ID artificial baseado no UUID
            locacao.setId(UUID.randomUUID().toString());
            // Insere o locacao no BD
            dao.insert(locacao);
        }
        else// locacao já existe: atualiza no BD
            dao.update(locacao);
    }

    private Locacao create(LocacaoDTO dto) {
        var resultado = new LocacaoBuilder()
                .comCPF(dto.cpf())
                .comPlaca(dto.placa())
                .comDataLocacao(dto.datalocacao())
                .build();

        // Assume que a criação foi bem sucedida,
        // pois os dados do BD devem estar consistentes
        var locacao= resultado.valor;

        // Seta o ID do objeto, pois ele veio do BD
        locacao.setId(dto.id());

        return locacao;
    }
}
