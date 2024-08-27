package domain.dao;

/**
     * Classe usada para transportar dados do VeiculoDAO para o VeiculoRepository
     */
public record VeiculoDTO(String id,
                         String placa,
                         String modelo,
                         Integer anoFabricacao,
                         Double valorDiaria,
                         Integer quilometragem){
}
