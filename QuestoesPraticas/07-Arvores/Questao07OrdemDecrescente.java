/**
 * Questao 07 - Imprimir em Ordem Decrescente.
 *
 * Imprime os valores de uma BST do maior para o menor.
 */
public class Questao07OrdemDecrescente {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class BST {
        private Nodo raiz;

        void inserir(int valor) {
            raiz = inserir(raiz, valor);
        }

        private Nodo inserir(Nodo nodo, int valor) {
            if (nodo == null) {
                return new Nodo(valor);
            }
            if (valor < nodo.valor) {
                nodo.esquerda = inserir(nodo.esquerda, valor);
            } else if (valor > nodo.valor) {
                nodo.direita = inserir(nodo.direita, valor);
            }
            return nodo;
        }

        void imprimirDecrescente() {
            imprimirDecrescente(raiz);
            System.out.println();
        }

        private void imprimirDecrescente(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            imprimirDecrescente(nodo.direita);
            System.out.print(nodo.valor + " ");
            imprimirDecrescente(nodo.esquerda);
        }
    }

    public static void main(String[] args) {
        BST arvore = new BST();
        int[] valores = { 20, 10, 30, 5, 15, 25, 40 };

        for (int valor : valores) {
            arvore.inserir(valor);
        }

        System.out.print("Ordem decrescente: ");
        arvore.imprimirDecrescente();
        System.out.println("Resultado esperado: 40 30 25 20 15 10 5");
    }
}
