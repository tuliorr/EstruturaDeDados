import Unidade2.P04FilasPrioridade.FilaPrioridadeHeap;
import Unidade2.P04FilasPrioridade.FilaPrioridadeNaoOrdenada;
import Unidade2.P04FilasPrioridade.FilaPrioridadeOrdenada;

/**
 * Questão 08 - Comparar Implementações.
 *
 * Conta chamadas a compareTo durante inserções e remoções nas três filas.
 */
public class Questao08CompararImplementacoes {

    private static class ValorContado implements Comparable<ValorContado> {
        static int comparacoes;
        int valor;

        ValorContado(int valor) {
            this.valor = valor;
        }

        static void zerarComparacoes() {
            comparacoes = 0;
        }

        @Override
        public int compareTo(ValorContado outro) {
            comparacoes++;
            return Integer.compare(valor, outro.valor);
        }
    }

    static class Resultado {
        int insercaoNaoOrdenada;
        int remocaoNaoOrdenada;
        int insercaoOrdenada;
        int remocaoOrdenada;
        int insercaoHeap;
        int remocaoHeap;

        @Override
        public String toString() {
            return "Não ordenada: " + insercaoNaoOrdenada + "/" + remocaoNaoOrdenada
                    + "\nOrdenada: " + insercaoOrdenada + "/" + remocaoOrdenada
                    + "\nHeap: " + insercaoHeap + "/" + remocaoHeap;
        }
    }

    public static Resultado comparar(int[] valores) {
        Resultado resultado = new Resultado();
        medirNaoOrdenada(valores, resultado);
        medirOrdenada(valores, resultado);
        medirHeap(valores, resultado);
        return resultado;
    }

    private static void medirNaoOrdenada(int[] valores, Resultado resultado) {
        FilaPrioridadeNaoOrdenada<ValorContado> fila = new FilaPrioridadeNaoOrdenada<>();
        ValorContado.zerarComparacoes();
        for (int valor : valores) {
            fila.enfileirar(new ValorContado(valor));
        }
        resultado.insercaoNaoOrdenada = ValorContado.comparacoes;

        ValorContado.zerarComparacoes();
        while (!fila.estaVazia()) {
            fila.desenfileirar();
        }
        resultado.remocaoNaoOrdenada = ValorContado.comparacoes;
    }

    private static void medirOrdenada(int[] valores, Resultado resultado) {
        FilaPrioridadeOrdenada<ValorContado> fila = new FilaPrioridadeOrdenada<>();
        ValorContado.zerarComparacoes();
        for (int valor : valores) {
            fila.enfileirar(new ValorContado(valor));
        }
        resultado.insercaoOrdenada = ValorContado.comparacoes;

        ValorContado.zerarComparacoes();
        while (!fila.estaVazia()) {
            fila.desenfileirar();
        }
        resultado.remocaoOrdenada = ValorContado.comparacoes;
    }

    private static void medirHeap(int[] valores, Resultado resultado) {
        if (valores.length == 0) {
            return;
        }

        FilaPrioridadeHeap<ValorContado> fila = new FilaPrioridadeHeap<>(valores.length);
        ValorContado.zerarComparacoes();
        for (int valor : valores) {
            fila.enfileirar(new ValorContado(valor));
        }
        resultado.insercaoHeap = ValorContado.comparacoes;

        ValorContado.zerarComparacoes();
        while (!fila.estaVazia()) {
            fila.desenfileirar();
        }
        resultado.remocaoHeap = ValorContado.comparacoes;
    }

    public static void main(String[] args) {
        int[] valores = { 4, 1, 5, 2, 3 };
        System.out.println(comparar(valores));
        System.out.println("Resultado esperado: 0/10, 8/0, 6/5");
    }
}
