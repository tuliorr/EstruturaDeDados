import Unidade2.P02Pilhas.PilhaEstatica;

/**
 * Questão 01 - Editor com apagar.
 *
 * O caractere '#' apaga o último caractere válido digitado. Uma pilha guarda
 * os caracteres porque o último digitado deve ser o primeiro removido.
 */
public class Questao01EditorComApagar {

    /**
     * Aplica todos os comandos de apagar e devolve o texto corrigido.
     */
    public static String aplicarEdicao(String texto) {
        if (texto.isEmpty()) {
            return "";
        }

        PilhaEstatica pilha = new PilhaEstatica(texto.length());

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);

            if (caractere == '#') {
                // Um apagar no início simplesmente não possui efeito.
                if (!pilha.estaVazia()) {
                    pilha.desempilhar();
                }
            } else {
                pilha.empilhar(caractere);
            }
        }

        char[] resultado = new char[pilha.tamanho()];
        for (int i = resultado.length - 1; i >= 0; i--) {
            resultado[i] = pilha.desempilhar();
        }
        return new String(resultado);
    }

    public static void main(String[] args) {
        String digitado = "algoritx#mo";

        System.out.println("Texto digitado: " + digitado);
        System.out.println("Texto corrigido: " + aplicarEdicao(digitado));
        System.out.println("Resultado esperado: algoritmo");

        // Experimente também "abc###d" e observe o resultado.
    }
}
