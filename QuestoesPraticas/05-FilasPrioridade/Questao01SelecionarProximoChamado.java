import Unidade2.P04FilasPrioridade.FilaPrioridadeNaoOrdenada;

/**
 * Questão 01 - Selecionar o Próximo Chamado.
 *
 * Usa uma fila de prioridade não ordenada para escolher o chamado mais urgente.
 */
public class Questao01SelecionarProximoChamado {

    static class Chamado implements Comparable<Chamado> {
        private String descricao;
        private int prioridade;

        Chamado(String descricao, int prioridade) {
            this.descricao = descricao;
            this.prioridade = prioridade;
        }

        @Override
        public int compareTo(Chamado outro) {
            return Integer.compare(prioridade, outro.prioridade);
        }

        @Override
        public String toString() {
            return descricao + "(p=" + prioridade + ")";
        }
    }

    public static Chamado selecionarProximo(Chamado[] chamados) {
        FilaPrioridadeNaoOrdenada<Chamado> fila = new FilaPrioridadeNaoOrdenada<>();
        for (Chamado chamado : chamados) {
            fila.enfileirar(chamado);
        }
        return fila.estaVazia() ? null : fila.desenfileirar();
    }

    public static void main(String[] args) {
        Chamado[] chamados = {
                new Chamado("Servidor", 3),
                new Chamado("Senha", 5),
                new Chamado("Impressora", 2)
        };

        System.out.println("Próximo chamado: " + selecionarProximo(chamados));
        System.out.println("Resultado esperado: Senha(p=5)");
    }
}
