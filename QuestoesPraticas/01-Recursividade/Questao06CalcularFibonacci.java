/**
 * Questão 06 - Calcular Fibonacci.
 *
 * Calcula recursivamente um termo da sequência de Fibonacci.
 */
public class Questao06CalcularFibonacci {

    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int posicao = 7;

        System.out.println("Fibonacci de " + posicao + ": " + fibonacci(posicao));
        System.out.println("Resultado esperado: 13");
    }
}
