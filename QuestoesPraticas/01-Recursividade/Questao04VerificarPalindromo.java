/**
 * Questão 04 - Verificar Palíndromo.
 *
 * Compara recursivamente os caracteres das duas extremidades de um texto.
 */
public class Questao04VerificarPalindromo {

    public static boolean ehPalindromo(String texto) {
        return compararExtremos(texto, 0, texto.length() - 1);
    }

    private static boolean compararExtremos(String texto, int esquerda, int direita) {
        if (esquerda >= direita) {
            return true;
        }
        if (texto.charAt(esquerda) != texto.charAt(direita)) {
            return false;
        }
        return compararExtremos(texto, esquerda + 1, direita - 1);
    }

    public static void main(String[] args) {
        String texto = "arara";

        System.out.println(texto + " é palíndromo: " + ehPalindromo(texto));
        System.out.println("Resultado esperado: true");
    }
}
