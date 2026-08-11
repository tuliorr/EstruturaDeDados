import Unidade2.P02Pilhas.PilhaEstatica;

/**
 * Questão 01 - Validar delimitadores.
 *
 * Verifica se parênteses, colchetes e chaves estão balanceados. O último
 * delimitador aberto deve ser o primeiro a ser fechado.
 */
public class Questao01ValidarDelimitadores {

    /**
     * Retorna true quando cada fechamento corresponde à abertura no topo.
     */
    public static boolean validar(String expressao) {
        if (expressao.isEmpty()) {
            return true;
        }

        PilhaEstatica pilha = new PilhaEstatica(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);

            if (caractere == '(' || caractere == '[' || caractere == '{') {
                pilha.empilhar(caractere);
            } else if (caractere == ')' || caractere == ']' || caractere == '}') {
                if (pilha.estaVazia() || !combinam(pilha.desempilhar(), caractere)) {
                    return false;
                }
            }
        }

        return pilha.estaVazia();
    }

    private static boolean combinam(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')')
                || (abertura == '[' && fechamento == ']')
                || (abertura == '{' && fechamento == '}');
    }

    public static void main(String[] args) {
        String expressao = "{[()]}";

        System.out.println("Expressão: " + expressao);
        System.out.println("Delimitadores válidos: " + validar(expressao));
        System.out.println("Resultado esperado: true");

        // Experimente trocar a expressão por "([)]".
    }
}
