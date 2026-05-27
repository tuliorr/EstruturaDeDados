/**
 * Questao 08 - Caminho ate um Valor.
 *
 * Imprime o caminho percorrido em uma busca dentro de uma BST.
 */
public class Questao08CaminhoAteValor {

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

        boolean imprimirCaminhoAte(int valor) {
            System.out.print("Caminho: ");
            boolean encontrado = imprimirCaminhoAte(raiz, valor);
            System.out.println();
            return encontrado;
        }

        private boolean imprimirCaminhoAte(Nodo nodo, int valor) {
            if (nodo == null) {
                return false;
            }

            System.out.print(nodo.valor + " ");

            if (valor == nodo.valor) {
                return true;
            }

            // A propriedade da BST decide qual lado pode conter o valor.
            if (valor < nodo.valor) {
                return imprimirCaminhoAte(nodo.esquerda, valor);
            }
            return imprimirCaminhoAte(nodo.direita, valor);
        }
    }

    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        int[] valores = { 40, 20, 60, 10, 30, 25 };

        for (int valor : valores) {
            arvore.inserir(valor);
        }

        boolean encontrado = arvore.imprimirCaminhoAte(25);
        System.out.println("Encontrado: " + encontrado);
        System.out.println("Resultado esperado: Caminho 40 20 30 25 / true");
    }
}
