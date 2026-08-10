import Unidade2.P03Filas.FilaDinamica;

/**
 * Questão 05 - Escalonamento Round Robin.
 *
 * Cada processo recebe no máximo um quantum de tempo. Se ainda possuir trabalho,
 * retorna ao fim da fila para esperar a próxima rodada.
 */
public class Questao05EscalonamentoRoundRobin {

    /** Representa um processo com nome e tempo restante. */
    public static class Processo {
        private final String nome;
        private int tempoRestante;

        public Processo(String nome, int tempo) {
            this.nome = nome;
            this.tempoRestante = tempo;
        }
    }

    /**
     * Executa os processos e retorna a ordem dos intervalos usados.
     */
    public static String escalonar(Processo[] processos, int quantum) {
        if (quantum <= 0) {
            throw new IllegalArgumentException("O quantum deve ser positivo.");
        }

        FilaDinamica<Processo> fila = new FilaDinamica<>();
        for (Processo processo : processos) {
            fila.enfileirar(processo);
        }

        StringBuilder ordem = new StringBuilder();
        while (!fila.estaVazia()) {
            Processo atual = fila.desenfileirar();
            int tempoExecutado = Math.min(quantum, atual.tempoRestante);
            atual.tempoRestante -= tempoExecutado;

            if (ordem.length() > 0) {
                ordem.append(" -> ");
            }
            ordem.append(atual.nome).append("(").append(tempoExecutado).append(")");

            if (atual.tempoRestante > 0) {
                fila.enfileirar(atual);
            }
        }
        return ordem.toString();
    }

    public static void main(String[] args) {
        Processo[] processos = {
            new Processo("P1", 5),
            new Processo("P2", 3),
            new Processo("P3", 1)
        };

        System.out.println("Ordem: " + escalonar(processos, 2));
        System.out.println("Resultado esperado: P1(2) -> P2(2) -> P3(1) -> "
                + "P1(2) -> P2(1) -> P1(1)");

        // Experimente alterar o quantum para 1 ou 3.
    }
}
