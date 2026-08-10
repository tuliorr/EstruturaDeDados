/**
 * Questão 03 - Verificar Número Primo.
 *
 * Verifica recursivamente se um número possui algum divisor além de 1 e dele
 * mesmo.
 */
public class Questao03VerificarPrimo {

    public static boolean ehPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        return testarDivisor(numero, 2);
    }

    private static boolean testarDivisor(int numero, int divisor) {
        if (divisor * divisor > numero) {
            return true;
        }
        if (numero % divisor == 0) {
            return false;
        }
        return testarDivisor(numero, divisor + 1);
    }

    public static void main(String[] args) {
        int numero = 29;

        System.out.println(numero + " é primo: " + ehPrimo(numero));
        System.out.println("Resultado esperado: true");
    }
}
