/**
 * Questao 04 - Contar Valores Maiores que X.
 *
 * Conta quantos valores de uma arvore binaria sao maiores que um limite
 * informado.
 */
public class Questao04ContarMaioresQueX {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    public static int contarMaioresQue(Nodo nodo, int x) {
        if (nodo == null) {
            return 0;
        }

        int quantidadeAtual = nodo.valor > x ? 1 : 0;
        int quantidadeEsquerda = contarMaioresQue(nodo.esquerda, x);
        int quantidadeDireita = contarMaioresQue(nodo.direita, x);

        return quantidadeAtual + quantidadeEsquerda + quantidadeDireita;
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(8);
        raiz.esquerda = new Nodo(3);
        raiz.direita = new Nodo(10);
        raiz.esquerda.esquerda = new Nodo(1);
        raiz.esquerda.direita = new Nodo(6);
        raiz.direita.direita = new Nodo(14);

        int x = 6;
        System.out.println("Quantidade de valores maiores que " + x + ": " + contarMaioresQue(raiz, x));
        System.out.println("Resultado esperado: 3");
    }
}
