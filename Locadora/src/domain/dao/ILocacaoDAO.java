package domain.dao;


import domain.Locacao;

import java.sql.SQLException;

public interface ILocacaoDAO {

    void insert(Locacao locacao) throws SQLException;

    void update(Locacao locacao) throws SQLException;

    LocacaoDTO findByCPFPlaca(long cpf,String placa) throws SQLException;

}
