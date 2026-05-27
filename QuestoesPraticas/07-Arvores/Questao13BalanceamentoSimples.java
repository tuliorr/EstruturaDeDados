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

        int altura() {
            return altura(raiz);
        }

        private int altura(Nodo nodo) {
            if (nodo == null) {
                return -1;
            }
            return 1 + Math.max(altura(nodo.esquerda), altura(nodo.direita));
        }

        boolean estaBalanceada() {
            return estaBalanceada(raiz);
        }

        private boolean estaBalanceada(Nodo nodo) {
            if (nodo == null) {
                return true;
            }

            int alturaEsquerda = altura(nodo.esquerda);
            int alturaDireita = altura(nodo.direita);
            int diferenca = Math.abs(alturaEsquerda - alturaDireita);

            // Se a diferenca passar de 1, este nodo ja quebra o balanceamento.
            if (diferenca > 1) {
                return false;
            }

            return estaBalanceada(nodo.esquerda) && estaBalanceada(nodo.direita);
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Nodo raiz = arvore.inserirRaiz(10);
        Nodo esquerda = arvore.inserirEsquerda(raiz, 5);
        arvore.inserirDireita(raiz, 20);
        arvore.inserirEsquerda(esquerda, 3);

        System.out.println("Balanceada: " + arvore.estaBalanceada());
        System.out.println("Altura: " + arvore.altura());
        System.out.println("Resultado esperado: true");
    }
}
