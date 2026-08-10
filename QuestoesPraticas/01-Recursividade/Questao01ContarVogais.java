/**
 * Questão 01 - Contar Vogais.
 *
 * Conta recursivamente quantas vogais existem em um texto.
 */
public class Questao01ContarVogais {

    public static int contarVogais(String texto) {
        return contarVogaisAPartirDe(texto, 0);
    }

    private static int contarVogaisAPartirDe(String texto, int posicao) {
        if (posicao == texto.length()) {
            return 0;
        }

        int quantidadeAtual = ehVogal(texto.charAt(posicao)) ? 1 : 0;
        return quantidadeAtual + contarVogaisAPartirDe(texto, posicao + 1);
    }

    private static boolean ehVogal(char caractere) {
        return "aeiouAEIOU".indexOf(caractere) >= 0;
    }

    public static void main(String[] args) {
        String texto = "estrutura";

        System.out.println("Vogais em \"" + texto + "\": " + contarVogais(texto));
        System.out.println("Resultado esperado: 4");
    }
}
