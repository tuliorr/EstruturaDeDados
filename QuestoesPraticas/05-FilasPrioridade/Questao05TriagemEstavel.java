import java.util.Arrays;

import Unidade2.P04FilasPrioridade.FilaPrioridadeHeapEstavel;

/**
 * Questão 05 - Triagem Estável de Pacientes.
 *
 * Pacientes mais urgentes saem primeiro. Em um empate, prevalece a chegada.
 */
public class Questao05TriagemEstavel {

    static class Paciente implements Comparable<Paciente> {
        private String nome;
        private int urgencia;

        Paciente(String nome, int urgencia) {
            this.nome = nome;
            this.urgencia = urgencia;
        }

        @Override
        public int compareTo(Paciente outro) {
            return Integer.compare(urgencia, outro.urgencia);
        }
    }

    public static String[] ordemAtendimento(Paciente[] pacientes) {
        if (pacientes.length == 0) {
            return new String[0];
        }

        FilaPrioridadeHeapEstavel<Paciente> fila = new FilaPrioridadeHeapEstavel<>(pacientes.length);
        for (Paciente paciente : pacientes) {
            fila.enfileirar(paciente);
        }

        String[] ordem = new String[pacientes.length];
        for (int i = 0; i < ordem.length; i++) {
            ordem[i] = fila.desenfileirar().nome;
        }
        return ordem;
    }

    public static void main(String[] args) {
        Paciente[] pacientes = {
                new Paciente("Ana", 4),
                new Paciente("Bruno", 5),
                new Paciente("Carla", 4),
                new Paciente("Diego", 5)
        };

        System.out.println("Atendimento: " + Arrays.toString(ordemAtendimento(pacientes)));
        System.out.println("Resultado esperado: [Bruno, Diego, Ana, Carla]");
    }
}
