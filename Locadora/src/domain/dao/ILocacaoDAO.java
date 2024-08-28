package domain.dao;


import domain.Locacao;

import java.sql.SQLException;
import java.util.List;

public interface ILocacaoDAO {

    void insert(Locacao locacao) throws SQLException;

    void update(Locacao locacao) throws SQLException;

    List<LocacaoDTO> findAll() throws SQLException;

    LocacaoDTO findByCPFPlaca(long cpf,String placa) throws SQLException;

}
