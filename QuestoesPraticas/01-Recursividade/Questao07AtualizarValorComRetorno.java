/**
 * Questão 07 - Atualizar Valor com Retorno.
 *
 * Mostra que alterar um parâmetro primitivo não modifica a variável usada na
 * chamada. O novo valor precisa ser retornado e atribuído.
 */
public class Questao07AtualizarValorComRetorno {

    public static double aplicarAcrescimo(double valor, double percentual) {
        return valor + valor * percentual / 100.0;
    }

    public static void main(String[] args) {
        double valorOriginal = 100.0;
        double valorAtualizado = aplicarAcrescimo(valorOriginal, 10.0);

        System.out.println("Valor original: " + valorOriginal);
        System.out.println("Valor atualizado: " + valorAtualizado);
        System.out.println("Resultado esperado: original 100.0 e atualizado 110.0");
    }
}
