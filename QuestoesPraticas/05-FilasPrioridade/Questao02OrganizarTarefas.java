import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeOrdenada;

/**
 * Questão 02 - Organizar Tarefas por Prioridade.
 *
 * Insere tarefas em uma fila ordenada e devolve a ordem de execução.
 */
public class Questao02OrganizarTarefas {

    static class Tarefa implements Comparable<Tarefa> {
        private String descricao;
        private int prioridade;

        Tarefa(String descricao, int prioridade) {
            this.descricao = descricao;
            this.prioridade = prioridade;
        }

        @Override
        public int compareTo(Tarefa outra) {
            return Integer.compare(prioridade, outra.prioridade);
        }
    }

    public static String[] ordenarTarefas(Tarefa[] tarefas) {
        FilaPrioridadeOrdenada<Tarefa> fila = new FilaPrioridadeOrdenada<>();
        for (Tarefa tarefa : tarefas) {
            fila.enfileirar(tarefa);
        }

        String[] ordem = new String[tarefas.length];
        for (int i = 0; i < ordem.length; i++) {
            ordem[i] = fila.desenfileirar().descricao;
        }
        return ordem;
    }

    public static void main(String[] args) {
        Tarefa[] tarefas = {
                new Tarefa("Email", 2),
                new Tarefa("Corrigir prova", 5),
                new Tarefa("Atualizar notas", 4)
        };

        System.out.println("Ordem: " + Arrays.toString(ordenarTarefas(tarefas)));
        System.out.println("Resultado esperado: [Corrigir prova, Atualizar notas, Email]");
    }
}
