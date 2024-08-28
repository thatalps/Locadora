package domain.dao;

import java.time.LocalDateTime;

public record LocacaoDTO (String id,
                          long cpf,
                          String placa,
                          LocalDateTime datalocacao){ }
