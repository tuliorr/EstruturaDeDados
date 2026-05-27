import Unidade2.P02Pilhas.PilhaDinamica;

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

        void imprimirCrescente() {
            imprimirCrescente(raiz);
            System.out.println();
        }

        private void imprimirCrescente(Nodo nodo) {
            if (nodo == null) {
                return;
            }
            imprimirCrescente(nodo.esquerda);
            System.out.print(nodo.valor + " ");
            imprimirCrescente(nodo.direita);
        }

        void imprimirDecrescenteRecursivo() {
            imprimirDecrescenteRecursivo(raiz);
            System.out.println();
        }

        private void imprimirDecrescenteRecursivo(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            // Na BST, visitar direita, raiz e esquerda gera ordem decrescente.
            imprimirDecrescenteRecursivo(nodo.direita);
            System.out.print(nodo.valor + " ");
            imprimirDecrescenteRecursivo(nodo.esquerda);
        }

        void imprimirDecrescenteIterativo() {
            PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
            Nodo atual = raiz;

            while (atual != null || !pilha.estaVazia()) {
                while (atual != null) {
                    pilha.empilhar(atual);
                    atual = atual.direita;
                }

                atual = pilha.desempilhar();
                System.out.print(atual.valor + " ");
                atual = atual.esquerda;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
        int[] valores = { 20, 10, 30, 5, 15, 25, 40 };

        for (int valor : valores) {
            arvore.inserir(valor);
        }

        System.out.print("Ordem decrescente (recursivo): ");
        arvore.imprimirDecrescenteRecursivo();
        System.out.print("Ordem decrescente (iterativo): ");
        arvore.imprimirDecrescenteIterativo();
        System.out.println("Resultado esperado: 40 30 25 20 15 10 5");
    }
}
