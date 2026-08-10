import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 02 - Desfazer operações.
 *
 * Cada número altera um total. O comando "desfazer" remove a última alteração
 * registrada, ilustrando a ordem LIFO de uma pilha.
 */
public class Questao02DesfazerOperacoes {

    /**
     * Processa números inteiros e comandos "desfazer", retornando o total final.
     */
    public static int processar(String[] comandos) {
        PilhaDinamica<Integer> operacoes = new PilhaDinamica<>();
        int total = 0;

        for (String comando : comandos) {
            if (comando.equals("desfazer")) {
                if (!operacoes.estaVazia()) {
                    total -= operacoes.desempilhar();
                }
            } else {
                int valor = Integer.parseInt(comando);
                operacoes.empilhar(valor);
                total += valor;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        String[] comandos = { "10", "-3", "5", "desfazer", "2" };

        System.out.println("Total após os comandos: " + processar(comandos));
        System.out.println("Resultado esperado: 9");

        // Experimente começar a lista com "desfazer".
    }
}
