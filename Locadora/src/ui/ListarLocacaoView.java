package ui;

import domain.dao.ListagemLocacaoDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListarLocacaoView {

    public void mostrarLocacoes(List<ListagemLocacaoDTO> locacaos) {

        if (locacaos.isEmpty())
            System.out.println("\nNão há locações cadastradas!");
        else {
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------");
            System.out.println("CPF                            NOME                           PLACA                MODELO               DATA/HORA");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            for (var c: locacaos) {

                // Definir um padrão de formatação
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

                System.out.printf("%-30d %-30s %-20s %-20s (%s)\n",
                        c.getCpf(),
                        c.getNome(),
                        c.getPlaca(),
                        c.getModelo(),
                        c.getDatalocacao().format(formatter));
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void mostrarErro() {
        System.out.println("Erro no acesso aos dados. Tente novamente ou procure o suporte!");
    }

}
