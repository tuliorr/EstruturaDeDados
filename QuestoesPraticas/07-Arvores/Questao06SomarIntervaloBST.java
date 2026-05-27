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

    private static class ArvoreBinariaBusca {
        private Nodo raiz;

        boolean estaVazia() {
            return raiz == null;
        }

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

        boolean buscar(int valor) {
            Nodo atual = raiz;
            while (atual != null) {
                if (valor == atual.valor) {
                    return true;
                }
                atual = valor < atual.valor ? atual.esquerda : atual.direita;
            }
            return false;
        }

        int somarIntervalo(int min, int max) {
            return somarIntervalo(raiz, min, max);
        }

        private int somarIntervalo(Nodo nodo, int min, int max) {
            if (nodo == null) {
                return 0;
            }

            // Na BST, valores menores ficam a esquerda e maiores ficam a direita.
            if (nodo.valor < min) {
                return somarIntervalo(nodo.direita, min, max);
            }
            if (nodo.valor > max) {
                return somarIntervalo(nodo.esquerda, min, max);
            }

            return nodo.valor
                    + somarIntervalo(nodo.esquerda, min, max)
                    + somarIntervalo(nodo.direita, min, max);
        }
    }

    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        int[] valores = { 20, 10, 30, 5, 15, 25, 40 };

        for (int valor : valores) {
            arvore.inserir(valor);
        }

        System.out.println("Soma no intervalo: " + arvore.somarIntervalo(12, 30));
        System.out.println("Resultado esperado: 90");
    }
}
