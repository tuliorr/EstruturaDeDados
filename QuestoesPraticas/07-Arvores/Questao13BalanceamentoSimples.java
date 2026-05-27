import Unidade2.P03Filas.FilaDinamica;

/**
 * Questao 13 - Verificar Balanceamento Simples.
 *
 * Verifica se, para cada nodo, a diferenca entre as alturas das subarvores e no
 * maximo 1.
 */
public class Questao13BalanceamentoSimples {

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

        private static class NodoComNivel {
            Nodo nodo;
            int nivel;

            NodoComNivel(Nodo nodo, int nivel) {
                this.nodo = nodo;
                this.nivel = nivel;
            }
        }

        int alturaRecursiva() {
            return alturaRecursiva(raiz);
        }

        private int alturaRecursiva(Nodo nodo) {
            if (nodo == null) {
                return -1;
            }
            return 1 + Math.max(alturaRecursiva(nodo.esquerda), alturaRecursiva(nodo.direita));
        }

        int alturaIterativa() {
            return alturaIterativa(raiz);
        }

        private int alturaIterativa(Nodo nodo) {
            if (nodo == null) {
                return -1;
            }

            int maiorNivel = -1;
            FilaDinamica<NodoComNivel> fila = new FilaDinamica<>();
            fila.enfileirar(new NodoComNivel(nodo, 0));

            while (!fila.estaVazia()) {
                NodoComNivel atual = fila.desenfileirar();
                maiorNivel = atual.nivel;

                if (atual.nodo.esquerda != null) {
                    fila.enfileirar(new NodoComNivel(atual.nodo.esquerda, atual.nivel + 1));
                }
                if (atual.nodo.direita != null) {
                    fila.enfileirar(new NodoComNivel(atual.nodo.direita, atual.nivel + 1));
                }
            }

            return maiorNivel;
        }

        boolean estaBalanceadaRecursivo() {
            return estaBalanceadaRecursivo(raiz);
        }

        private boolean estaBalanceadaRecursivo(Nodo nodo) {
            if (nodo == null) {
                return true;
            }

            int alturaEsquerda = alturaRecursiva(nodo.esquerda);
            int alturaDireita = alturaRecursiva(nodo.direita);
            int diferenca = Math.abs(alturaEsquerda - alturaDireita);

            // Se a diferenca passar de 1, este nodo ja quebra o balanceamento.
            if (diferenca > 1) {
                return false;
            }

            return estaBalanceadaRecursivo(nodo.esquerda) && estaBalanceadaRecursivo(nodo.direita);
        }

        boolean estaBalanceadaIterativo() {
            if (raiz == null) {
                return true;
            }

            FilaDinamica<Nodo> fila = new FilaDinamica<>();
            fila.enfileirar(raiz);

            while (!fila.estaVazia()) {
                Nodo atual = fila.desenfileirar();
                int alturaEsquerda = alturaIterativa(atual.esquerda);
                int alturaDireita = alturaIterativa(atual.direita);

                if (Math.abs(alturaEsquerda - alturaDireita) > 1) {
                    return false;
                }
                if (atual.esquerda != null) {
                    fila.enfileirar(atual.esquerda);
                }
                if (atual.direita != null) {
                    fila.enfileirar(atual.direita);
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);

        System.out.println("Balanceada (recursivo): " + arvore.estaBalanceadaRecursivo());
        System.out.println("Balanceada (iterativo): " + arvore.estaBalanceadaIterativo());
        System.out.println("Altura (recursiva): " + arvore.alturaRecursiva());
        System.out.println("Altura (iterativa): " + arvore.alturaIterativa());
        System.out.println("Resultado esperado: true");
    }
}
