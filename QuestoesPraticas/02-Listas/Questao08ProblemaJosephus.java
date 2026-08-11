/**
 * Questão 08 - Problema de Josephus.
 *
 * Elimina participantes de uma lista circular até restar um sobrevivente.
 */
public class Questao08ProblemaJosephus {

    private static class Nodo {
        String nome;
        Nodo proximo;

        Nodo(String nome) {
            this.nome = nome;
        }
    }

    private static class Roda {
        Nodo atual;
        Nodo anterior;
        int tamanho;

        void adicionar(String nome) {
            Nodo novo = new Nodo(nome);
            if (atual == null) {
                atual = novo;
                anterior = novo;
                novo.proximo = novo;
            } else {
                novo.proximo = atual;
                anterior.proximo = novo;
                anterior = novo;
            }
            tamanho++;
        }

        void avancar() {
            anterior = atual;
            atual = atual.proximo;
        }

        String eliminarAtual() {
            String eliminado = atual.nome;
            anterior.proximo = atual.proximo;
            atual = atual.proximo;
            tamanho--;
            return eliminado;
        }
    }

    public static String resolverJosephus(String[] participantes, int passo) {
        Roda roda = new Roda();
        for (String participante : participantes) {
            roda.adicionar(participante);
        }

        while (roda.tamanho > 1) {
            for (int i = 1; i < passo; i++) {
                roda.avancar();
            }
            System.out.println("Eliminado: " + roda.eliminarAtual());
        }
        return roda.atual.nome;
    }

    public static void main(String[] args) {
        String[] participantes = {"Ana", "Beto", "Carlos", "Dora", "Eva"};
        String sobrevivente = resolverJosephus(participantes, 3);

        System.out.println("Sobrevivente: " + sobrevivente);
        System.out.println("Resultado esperado: Dora");
    }
}
