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

        void imprimirFolhas() {
            imprimirFolhas(raiz);
            System.out.println();
        }

        private void imprimirFolhas(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            // Folha e o nodo que nao possui nenhum filho.
            if (nodo.esquerda == null && nodo.direita == null) {
                System.out.print(nodo.valor + " ");
                return;
            }

            imprimirFolhas(nodo.esquerda);
            imprimirFolhas(nodo.direita);
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

        System.out.print("Folhas: ");
        arvore.imprimirFolhas();
        System.out.println("Quantidade de folhas: " + arvore.contarFolhas());
        System.out.println("Resultado esperado: 3 15 30");
    }
}
