package domain;

public class Placa {
    private String valor;

    /**
     * Valida e cria o Placa
     * @return Placa ou null, se número não é valido
     */
    private Placa(String valor) {
        super();
        this.valor = valor;
    }

    public static Placa create(String valor) {
        // Verificar se numero é de uma placa válida
        // Se for retorna o objeto placa
        // senão retorna null
        return ehPlacaValida(valor) ? new Placa(valor) : null;
    }
    private static boolean ehPlacaValida(String valor)
    {
        //verifica estrutura placa
        //Placa: deve ter o formato AAA9999
        // onde AAA são letras (A-Z ou a-z) e 9999 são dígitos.
        //Letras devem ser convertidas para caixa alta.
        if(valor.length()!=7)
            return false;
        // Converter para maiúsculas
        valor = valor.toUpperCase();
        // Expressão regular para validar o formato AAA9999
        String regex = "^[A-Z]{3}\\d{4}$";
        // Verificar se a placa corresponde ao padrão
        return valor.matches(regex);
    }
}
