/**
 * Questao 09 - Inverter os Filhos de Cada Nodo.
 *
 * Troca o filho esquerdo pelo direito em todos os nodos, criando o espelho da
 * arvore original.
 */
public class Questao09InverterFilhos {

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

        void inverterFilhos() {
            inverterFilhos(raiz);
        }

        private void inverterFilhos(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            // Troca local: depois a mesma ideia e aplicada nas subarvores.
            Nodo auxiliar = nodo.esquerda;
            nodo.esquerda = nodo.direita;
            nodo.direita = auxiliar;

            inverterFilhos(nodo.esquerda);
            inverterFilhos(nodo.direita);
        }

        void imprimirPreOrdem() {
            imprimirPreOrdem(raiz);
            System.out.println();
        }

        private void imprimirPreOrdem(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            System.out.print(nodo.valor + " ");
            imprimirPreOrdem(nodo.esquerda);
            imprimirPreOrdem(nodo.direita);
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

        arvore.inverterFilhos();

        System.out.print("Pre-ordem depois de inverter: ");
        arvore.imprimirPreOrdem();
        System.out.println("Resultado esperado: 10 20 30 15 5 3");
    }
}
