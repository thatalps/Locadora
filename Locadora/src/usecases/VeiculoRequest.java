package usecases;

/**
 * DTO usado para transportar os dados do PRESENTER para o CONTROLLER
 */
public record VeiculoRequest (String placa,
                             String modelo,
                             Integer anoFabricacao,
                             Double valorDiaria,
                             Integer quilometragem){
}
