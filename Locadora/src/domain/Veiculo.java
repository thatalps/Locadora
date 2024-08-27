package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Veiculo  extends Persistent {
    private String placa;
    private String modelo;
    private Integer anoFabricacao;
    private Double valorDiaria;
    private Integer quilometragem;

    /**
     * Construtor privado que obriga a criar o objeto usando o método 'create'
     */
    private Veiculo(String placa, String modelo, Integer anoFabricacao, Double valorDiaria, Integer quilometragem) {
        super();
        this.placa=placa;
        this.modelo=modelo;
        this.anoFabricacao=anoFabricacao;
        this.valorDiaria=valorDiaria;
        this.quilometragem=quilometragem;
    }

    /**
     * Valida os dados e cria um veiculo
     * @return Cliente ou lista de códigos de erro
     */
    public static Resultado<Veiculo> create(String numeroPlaca,
                                            String modelo,
                                            Integer anoFabricacao,
                                            Double valorDiaria,
                                            Integer quilometragem){
        List<Erro> erros = new ArrayList<>();
        Placa placa = null;

        // Tentar construir o objeto placa
        if ( numeroPlaca == null) {
            erros.add(Erro.PLACA_INVALIDA);
        }
        else {
            placa = placa.create(numeroPlaca);
            if (placa == null) {
                erros.add(Erro.PLACA_INVALIDA);
            }
        }

        // Valida o modelo
        if (modelo == null || modelo.length() < 3 || modelo.length() > 30) {
            erros.add(Erro.MODELO_INVALIDO);
        }

        //Valida o ano de fabricacao
        int anoAtual = LocalDate.now().getYear();
        if (anoFabricacao == null || anoFabricacao<2000 || anoFabricacao > anoAtual) {
            erros.add(Erro.ANO_FABRICACAO_INVALIDO);
        }

        // Verifica se valor diaria é valido
        if (valorDiaria == null || valorDiaria < 0 )
            erros.add(Erro.VALOR_DIARIA_INVALIDO);

        // Verifica se a km é valida
        if (quilometragem == null || quilometragem < 0 )
            erros.add(Erro.QUILOMETRAGEM_INVALIDA);

        // Retorna o veiculo ou a lista de códigos de erro
        return erros.isEmpty() ?
                Resultado.ok(new Veiculo(numeroPlaca,
                                         modelo,
                                         anoFabricacao,
                                         valorDiaria,
                                         quilometragem)) : Resultado.erro(erros);
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }
}