/**
 * Questão 05 - Converter Decimal para Binário.
 *
 * Converte recursivamente um inteiro não negativo para sua representação
 * binária.
 */
public class Questao05DecimalParaBinario {

    public static String decimalParaBinario(int numero) {
        if (numero < 2) {
            return String.valueOf(numero);
        }
        return decimalParaBinario(numero / 2) + (numero % 2);
    }

    public static void main(String[] args) {
        int numero = 13;

        System.out.println(numero + " em binário: " + decimalParaBinario(numero));
        System.out.println("Resultado esperado: 1101");
    }
}
