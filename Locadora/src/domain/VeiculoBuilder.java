package domain;

import java.util.ArrayList;
import java.util.List;

public class VeiculoBuilder {
    private String placa;
    private String modelo;
    private Integer anoFabricacao;
    private Double valorDiaria;
    private Integer quilometragem;

    public VeiculoBuilder() {}

    public VeiculoBuilder comPlaca(String placa) {
        this.placa=placa;
        return this;

    }  public VeiculoBuilder comModelo(String modelo) {
        this.modelo=modelo;
        return this;
    }
    public VeiculoBuilder comAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao=anoFabricacao;
        return this;
    }
    public VeiculoBuilder comValorDiaria(Double valorDiaria) {
        this.valorDiaria=valorDiaria;
        return this;
    }
    public VeiculoBuilder comQuilometragem(Integer km) {
        this.quilometragem=km;
        return this;
    }
    public  Resultado<Veiculo> build()
    {
        List<Erro> erros = new ArrayList<>();

        var resultVeiculo = Veiculo.create(placa.toUpperCase(),
                                            modelo,
                                            anoFabricacao,
                                            valorDiaria,
                                            quilometragem
                );

        // Se falhou, copia os códigos de erro para a lista de erros
        if (resultVeiculo.falha())
            erros.addAll(resultVeiculo.erros);

        // Retorna o veiculo ou a lista de códigos de erro
        return erros.isEmpty() ?
                Resultado.ok(resultVeiculo.valor) :
                Resultado.erro(erros);


    }

}
