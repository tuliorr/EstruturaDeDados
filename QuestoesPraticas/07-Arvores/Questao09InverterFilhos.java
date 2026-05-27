import Unidade2.P03Filas.FilaDinamica;

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

        void inverterFilhosRecursivo() {
            inverterFilhosRecursivo(raiz);
        }

        private void inverterFilhosRecursivo(Nodo nodo) {
            if (nodo == null) {
                return;
            }

            // Troca local: depois a mesma ideia e aplicada nas subarvores.
            Nodo auxiliar = nodo.esquerda;
            nodo.esquerda = nodo.direita;
            nodo.direita = auxiliar;

            inverterFilhosRecursivo(nodo.esquerda);
            inverterFilhosRecursivo(nodo.direita);
        }

        void inverterFilhosIterativo() {
            if (raiz == null) {
                return;
            }

            FilaDinamica<Nodo> fila = new FilaDinamica<>();
            fila.enfileirar(raiz);

            while (!fila.estaVazia()) {
                Nodo atual = fila.desenfileirar();
                Nodo auxiliar = atual.esquerda;
                atual.esquerda = atual.direita;
                atual.direita = auxiliar;

                if (atual.esquerda != null) {
                    fila.enfileirar(atual.esquerda);
                }
                if (atual.direita != null) {
                    fila.enfileirar(atual.direita);
                }
            }
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

    private static ArvoreBinaria criarArvoreExemplo() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        Nodo direita = arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);
        arvore.inserirEsquerda(direita, 15);
        arvore.inserirDireita(direita, 30);
        return arvore;
    }

    public static void main(String[] args) {
        ArvoreBinaria arvoreRecursiva = criarArvoreExemplo();
        ArvoreBinaria arvoreIterativa = criarArvoreExemplo();

        arvoreRecursiva.inverterFilhosRecursivo();

        System.out.print("Pre-ordem depois de inverter (recursivo): ");
        arvoreRecursiva.imprimirPreOrdem();

        arvoreIterativa.inverterFilhosIterativo();
        System.out.print("Pre-ordem depois de inverter (iterativo): ");
        arvoreIterativa.imprimirPreOrdem();
        System.out.println("Resultado esperado: 10 20 30 15 5 3");
    }
}
