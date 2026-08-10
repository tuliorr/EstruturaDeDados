import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 07 - Pilha com mínimo.
 *
 * Uma segunda pilha guarda os menores valores encontrados e permite consultar
 * o mínimo atual em O(1), inclusive quando o menor valor aparece repetido.
 */
public class Questao07PilhaComMinimo {

    /**
     * Pilha de inteiros que oferece consulta ao menor elemento em tempo constante.
     */
    public static class PilhaComMinimo {
        private final PilhaDinamica<Integer> valores = new PilhaDinamica<>();
        private final PilhaDinamica<Integer> minimos = new PilhaDinamica<>();

        public void empilhar(int valor) {
            valores.empilhar(valor);
            if (minimos.estaVazia() || valor <= minimos.consultarTopo()) {
                minimos.empilhar(valor);
            }
        }

        public int desempilhar() {
            int removido = valores.desempilhar();
            if (removido == minimos.consultarTopo()) {
                minimos.desempilhar();
            }
            return removido;
        }

        public int minimo() {
            return minimos.consultarTopo();
        }
    }

    public static void main(String[] args) {
        PilhaComMinimo pilha = new PilhaComMinimo();
        pilha.empilhar(5);
        pilha.empilhar(2);
        pilha.empilhar(2);
        pilha.empilhar(4);

        System.out.println("Mínimo inicial: " + pilha.minimo());
        pilha.desempilhar();
        pilha.desempilhar();
        System.out.println("Mínimo após remover 4 e um 2: " + pilha.minimo());
        System.out.println("Resultados esperados: 2 e 2");

        // Experimente remover também o segundo valor 2.
    }
}
