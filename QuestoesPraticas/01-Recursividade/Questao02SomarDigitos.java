/**
 * Questão 02 - Somar Dígitos.
 *
 * Soma recursivamente todos os dígitos de um número não negativo.
 */
public class Questao02SomarDigitos {

    public static int somarDigitos(int numero) {
        if (numero < 10) {
            return numero;
        }
        return numero % 10 + somarDigitos(numero / 10);
    }

    public static void main(String[] args) {
        int numero = 5072;

        System.out.println("Soma dos dígitos de " + numero + ": " + somarDigitos(numero));
        System.out.println("Resultado esperado: 14");
    }
}
