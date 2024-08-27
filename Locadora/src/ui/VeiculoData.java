package ui;

/**
 * DTO usado para transportar os dados da VIEW para o PRESENTER
 */
public record VeiculoData ( String placa,
                         String modelo,
                         String anoFabricacao,
                         String valorDiaria,
                         String quilometragem){ }
