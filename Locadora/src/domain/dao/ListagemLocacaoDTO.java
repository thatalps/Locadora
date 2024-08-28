package domain.dao;

import domain.Persistent;

import java.time.LocalDateTime;

public class ListagemLocacaoDTO extends Persistent {
    private Long cpf;
    private String nome;
    private String placa;
    private String modelo;
    private LocalDateTime datalocacao;

    public ListagemLocacaoDTO() {
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getDatalocacao() {
        return datalocacao;
    }

    public void setDatalocacao(LocalDateTime datalocacao) {
        this.datalocacao = datalocacao;
    }



}
