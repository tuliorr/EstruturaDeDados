/**
 * Questao 06 - Somar Intervalo na BST.
 *
 * Soma os valores de uma arvore binaria de busca que estao dentro de um
 * intervalo fechado.
 */
public class Questao06SomarIntervaloBST {

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

        int somarIntervalo(int min, int max) {
            return somarIntervalo(raiz, min, max);
        }

        private int somarIntervalo(Nodo nodo, int min, int max) {
            if (nodo == null) {
                return 0;
            }

            if (nodo.valor < min) {
                return somarIntervalo(nodo.direita, min, max);
            }
            if (nodo.valor > max) {
                return somarIntervalo(nodo.esquerda, min, max);
            }

            int somaEsquerda = somarIntervalo(nodo.esquerda, min, max);
            int somaDireita = somarIntervalo(nodo.direita, min, max);

            return nodo.valor + somaEsquerda + somaDireita;
        }
    }

    public static void main(String[] args) {
        BST arvore = new BST();
        int[] valores = { 20, 10, 30, 5, 15, 25, 40 };

        for (int valor : valores) {
            arvore.inserir(valor);
        }

        System.out.println("Soma no intervalo: " + arvore.somarIntervalo(12, 30));
        System.out.println("Resultado esperado: 90");
    }
}
