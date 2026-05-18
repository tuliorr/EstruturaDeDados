/**
 * Questao 10 - Comparar Formato de Duas Arvores.
 *
 * Verifica se duas arvores possuem o mesmo formato, ignorando os valores.
 */
public class Questao10CompararFormato {

    private static class Nodo {
        int valor;
        Nodo esquerda;
        Nodo direita;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    public static boolean mesmoFormato(Nodo primeiro, Nodo segundo) {
        if (primeiro == null && segundo == null) {
            return true;
        }
        if (primeiro == null || segundo == null) {
            return false;
        }

        boolean mesmoFormatoEsquerda = mesmoFormato(primeiro.esquerda, segundo.esquerda);
        boolean mesmoFormatoDireita = mesmoFormato(primeiro.direita, segundo.direita);

        return mesmoFormatoEsquerda && mesmoFormatoDireita;
    }

    public static void main(String[] args) {
        Nodo arvoreA = new Nodo(10);
        arvoreA.esquerda = new Nodo(5);
        arvoreA.direita = new Nodo(20);

        Nodo arvoreB = new Nodo(8);
        arvoreB.esquerda = new Nodo(2);
        arvoreB.direita = new Nodo(9);

        System.out.println("Mesmo formato: " + mesmoFormato(arvoreA, arvoreB));
        System.out.println("Resultado esperado: true");
    }
}
