import Unidade2.P02Pilhas.PilhaEstatica;

/**
 * Questão 03 - Remover pares adjacentes.
 *
 * Sempre que dois caracteres iguais ficam lado a lado, o par e removido. A
 * pilha permite comparar cada novo caractere com o último ainda preservado.
 */
public class Questao03RemoverParesAdjacentes {

    /**
     * Remove pares iguais até que nenhum par adjacente permaneça.
     */
    public static String removerPares(String texto) {
        if (texto.isEmpty()) {
            return "";
        }

        PilhaEstatica pilha = new PilhaEstatica(texto.length());

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            if (!pilha.estaVazia() && pilha.consultarTopo() == caractere) {
                pilha.desempilhar();
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
        String texto = "abbaca";

        System.out.println("Texto original: " + texto);
        System.out.println("Após remover os pares: " + removerPares(texto));
        System.out.println("Resultado esperado: ca");

        // Experimente "azxxzy": as remoções formam novos pares.
    }
}
