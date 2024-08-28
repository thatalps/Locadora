package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocacaoBuilder {
    private Long cpf;
    private String placa;
    private LocalDateTime datalocacao;

    public LocacaoBuilder comCPF(Long cpf)
    {
        this.cpf=cpf;
        return this;
    }
    public LocacaoBuilder comPlaca(String placa)
    {
        this.placa=placa;
        return this;
    }
    public LocacaoBuilder comDataLocacao(LocalDateTime dataLocacao)
    {
        this.datalocacao=dataLocacao;
        return this;
    }

    public  Resultado<Locacao> build()
    {
        List<Erro> erros = new ArrayList<>();

        var resultLocacao = Locacao.create(cpf,
                                    placa.toUpperCase(),
                                    datalocacao);
        // Se falhou, copia os códigos de erro para a lista de erros
        if (resultLocacao.falha())
            erros.addAll(resultLocacao.erros);

        // Retorna o veiculo ou a lista de códigos de erro
        return erros.isEmpty() ?
                Resultado.ok(resultLocacao.valor) :
                Resultado.erro(erros);
    }
}
