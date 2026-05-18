/**
 * Questao 09 - Inverter os Filhos de Cada No.
 *
 * Troca o filho esquerdo pelo direito em todos os nos, criando o espelho da
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

    public static void inverterFilhos(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        Nodo auxiliar = nodo.esquerda;
        nodo.esquerda = nodo.direita;
        nodo.direita = auxiliar;

        inverterFilhos(nodo.esquerda);
        inverterFilhos(nodo.direita);
    }

    public static void imprimirPreOrdem(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        System.out.print(nodo.valor + " ");
        imprimirPreOrdem(nodo.esquerda);
        imprimirPreOrdem(nodo.direita);
    }

    public static void main(String[] args) {
        Nodo raiz = new Nodo(10);
        raiz.esquerda = new Nodo(5);
        raiz.direita = new Nodo(20);
        raiz.esquerda.esquerda = new Nodo(3);
        raiz.direita.esquerda = new Nodo(15);
        raiz.direita.direita = new Nodo(30);

        inverterFilhos(raiz);

        System.out.print("Pre-ordem depois de inverter: ");
        imprimirPreOrdem(raiz);
        System.out.println();
        System.out.println("Resultado esperado: 10 20 30 15 5 3");
    }
}
