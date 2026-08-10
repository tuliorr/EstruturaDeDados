import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 06 - Fila com duas pilhas.
 *
 * A pilha de entrada recebe novos elementos. A transferência para a pilha de
 * saída acontece somente quando ela fica vazia, preservando a ordem FIFO.
 */
public class Questao06FilaComDuasPilhas {

    /** Implementação genérica de fila usando somente duas pilhas. */
    public static class FilaComDuasPilhas<T> {
        private final PilhaDinamica<T> entrada = new PilhaDinamica<>();
        private final PilhaDinamica<T> saida = new PilhaDinamica<>();

        public boolean estaVazia() {
            return entrada.estaVazia() && saida.estaVazia();
        }

        public void enfileirar(T elemento) {
            entrada.empilhar(elemento);
        }

        public T desenfileirar() {
            transferirSeNecessario();
            return saida.desempilhar();
        }

        public T consultarFrente() {
            transferirSeNecessario();
            return saida.consultarTopo();
        }

        private void transferirSeNecessario() {
            if (saida.estaVazia()) {
                while (!entrada.estaVazia()) {
                    saida.empilhar(entrada.desempilhar());
                }
            }
        }
    }

    public static void main(String[] args) {
        FilaComDuasPilhas<String> fila = new FilaComDuasPilhas<>();
        fila.enfileirar("Ana");
        fila.enfileirar("Bruno");
        fila.enfileirar("Carla");

        System.out.println("Primeiro removido: " + fila.desenfileirar());
        fila.enfileirar("Daniel");
        System.out.println("Nova frente: " + fila.consultarFrente());
        System.out.println("Resultados esperados: Ana e Bruno");

        // Experimente alternar vários enfileiramentos e desenfileiramentos.
    }
}
