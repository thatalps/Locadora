package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Locacao extends Persistent{
    private long cpf;
    private String placa;
    private LocalDateTime dataLocacao;

    public Locacao(Long cpf, String placa, LocalDateTime dataLocacao) {
        this.cpf = cpf;
        this.placa = placa;
        this.dataLocacao = dataLocacao;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public static Resultado<Locacao> create(Long cpf,
                                            String numeroPlaca,
                                            LocalDateTime datalocacao){
        List<Erro> erros = new ArrayList<>();
        Placa placa = null;

        // valida placa
        if ( numeroPlaca == null) {
            erros.add(Erro.PLACA_INVALIDA);
        }
        else {
            placa = placa.create(numeroPlaca);
            if (placa == null) {
                erros.add(Erro.PLACA_INVALIDA);
            }
        }

//         Valida o cpf
        CPF cpfcriado = null;
        if (cpf == null)
            erros.add(Erro.CPF_INVALIDO);
        else {
            cpfcriado = CPF.create(cpf);
            if (cpfcriado == null)
                erros.add(Erro.CPF_INVALIDO);
        }

//         Retorna a locacao ou a lista de códigos de erro
        return erros.isEmpty() ?
                Resultado.ok(new Locacao(cpf, numeroPlaca, datalocacao)) : Resultado.erro(erros);
    }

}
