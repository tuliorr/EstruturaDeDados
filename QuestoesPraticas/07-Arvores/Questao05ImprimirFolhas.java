/**
 * Questao 05 - Imprimir Folhas.
 *
 * Imprime os valores dos nos folha de uma arvore binaria.
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

    public static void imprimirFolhas(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        if (nodo.esquerda == null && nodo.direita == null) {
            System.out.print(nodo.valor + " ");
            return;
        }

        imprimirFolhas(nodo.esquerda);
        imprimirFolhas(nodo.direita);
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(10);
        raiz.esquerda = new Nodo(5);
        raiz.direita = new Nodo(20);
        raiz.esquerda.esquerda = new Nodo(3);
        raiz.direita.esquerda = new Nodo(15);
        raiz.direita.direita = new Nodo(30);

        System.out.print("Folhas: ");
        imprimirFolhas(raiz);
        System.out.println();
        System.out.println("Resultado esperado: 3 15 30");
    }
}
