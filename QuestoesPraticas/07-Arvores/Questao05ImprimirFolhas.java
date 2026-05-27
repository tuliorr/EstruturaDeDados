import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questao 05 - Imprimir Folhas.
 *
 * Imprime os valores dos nodos folha de uma arvore binaria.
 */
public class Questao05ImprimirFolhas {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private static class ArvoreBinaria {
        private Nodo raiz;

        Nodo inserirRaiz(int valor) {
            raiz = new Nodo(valor);
            return raiz;
        }

        Nodo inserirEsquerda(Nodo pai, int valor) {
            pai.esquerda = new Nodo(valor);
            return pai.esquerda;
        }

        Nodo inserirDireita(Nodo pai, int valor) {
            pai.direita = new Nodo(valor);
            return pai.direita;
        }

        int contarFolhas() {
            return contarFolhas(raiz);
        }

        private int contarFolhas(Nodo nodo) {
            if (nodo == null) {
                return 0;
            }
            if (nodo.esquerda == null && nodo.direita == null) {
                return 1;
            }
            return contarFolhas(nodo.esquerda) + contarFolhas(nodo.direita);
        }

        void imprimirFolhasRecursivo() {
            imprimirFolhasRecursivo(raiz);
            System.out.println();
        }

        private void imprimirFolhasRecursivo(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            // Folha e o nodo que nao possui nenhum filho.
            if (nodo.esquerda == null && nodo.direita == null) {
                System.out.print(nodo.valor + " ");
                return;
            }

            imprimirFolhasRecursivo(nodo.esquerda);
            imprimirFolhasRecursivo(nodo.direita);
        }

        void imprimirFolhasIterativo() {
            if (raiz == null) {
                System.out.println();
                return;
            }

            PilhaDinamica<Nodo> pilha = new PilhaDinamica<>();
            pilha.empilhar(raiz);

            while (!pilha.estaVazia()) {
                Nodo atual = pilha.desempilhar();
                if (atual.esquerda == null && atual.direita == null) {
                    System.out.print(atual.valor + " ");
                }
                if (atual.direita != null) {
                    pilha.empilhar(atual.direita);
                }
                if (atual.esquerda != null) {
                    pilha.empilhar(atual.esquerda);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        Nodo direita = arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);
        arvore.inserirEsquerda(direita, 15);
        arvore.inserirDireita(direita, 30);

        System.out.print("Folhas (recursivo): ");
        arvore.imprimirFolhasRecursivo();
        System.out.print("Folhas (iterativo): ");
        arvore.imprimirFolhasIterativo();
        System.out.println("Quantidade de folhas: " + arvore.contarFolhas());
        System.out.println("Resultado esperado: 3 15 30");
    }
}
