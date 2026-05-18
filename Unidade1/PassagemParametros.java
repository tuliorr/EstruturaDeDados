package Unidade1;

/**
 * Demonstra como Java copia valores em atribuicoes e chamadas de metodos.
 *
 * A ideia principal e: Java sempre passa parametros por valor. Quando o dado e
 * primitivo, o valor copiado e o proprio numero, booleano, caractere etc.
 * Quando o dado e um objeto, o valor copiado e a referencia para esse objeto.
 */
public class PassagemParametros {

    // Classe pequena usada para enxergar quando duas variaveis apontam para o
    // mesmo objeto.
    private static class Pessoa {
        private String nome;

        private Pessoa(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return nome;
        }
    }

    // =========================
    // Tipos primitivos
    // =========================

    /**
     * Recebe uma copia do valor de numero. Alterar essa copia nao modifica a
     * variavel original usada na chamada.
     */
    public static void alterarNumero(int numero) {
        numero = 99;
        System.out.println("Dentro de alterarNumero: numero = " + numero);
    }

    // =========================
    // Objetos
    // =========================

    /**
     * Recebe uma copia da referencia, mas essa copia aponta para o mesmo objeto.
     * Por isso, alterar um atributo do objeto fica visivel fora do metodo.
     */
    public static void alterarNome(Pessoa pessoa) {
        pessoa.nome = "Nome alterado";
        System.out.println("Dentro de alterarNome: pessoa = " + pessoa);
    }

    /**
     * Reatribuir o parametro faz a copia da referencia apontar para outro objeto.
     * A variavel original, que esta fora do metodo, continua apontando para o
     * objeto antigo.
     */
    public static void substituirPessoa(Pessoa pessoa) {
        pessoa = new Pessoa("Pessoa criada dentro do metodo");
        System.out.println("Dentro de substituirPessoa: pessoa = " + pessoa);
    }

    /**
     * A tentativa de troca altera apenas as copias das referencias recebidas como
     * parametros. As variaveis originais usadas na chamada nao sao trocadas.
     */
    public static void tentarTrocarPessoas(Pessoa primeira, Pessoa segunda) {
        Pessoa auxiliar = primeira;
        primeira = segunda;
        segunda = auxiliar;

        System.out.println("Dentro de tentarTrocarPessoas:");
        System.out.println("primeira = " + primeira);
        System.out.println("segunda = " + segunda);
    }

    // =========================
    // Vetores
    // =========================

    /**
     * Vetores tambem sao objetos. Alterar uma posicao modifica o mesmo vetor
     * observado por quem chamou o metodo.
     */
    public static void alterarPrimeiraPosicao(int[] vetor) {
        vetor[0] = 100;
        System.out.println("Dentro de alterarPrimeiraPosicao: vetor[0] = " + vetor[0]);
    }

    /**
     * Criar um novo vetor e atribuir ao parametro altera apenas a copia da
     * referencia. O vetor original de fora do metodo nao e substituido.
     */
    public static void substituirVetor(int[] vetor) {
        vetor = new int[] { 7, 8, 9 };
        System.out.println("Dentro de substituirVetor: vetor[0] = " + vetor[0]);
    }

    // =========================
    // Teste dos exemplos
    // =========================

    public static void main(String[] args) {
        System.out.println("=== Passagem de Parametros em Java ===");

        // Atribuicao com primitivos: b recebe uma copia do valor de a.
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("Depois de b = 20:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        // Parametro primitivo: o metodo altera apenas a copia recebida.
        alterarNumero(a);
        System.out.println("Depois de alterarNumero(a): a = " + a);

        // Atribuicao com objetos: aluno e mesmoAluno guardam referencias para o
        // mesmo objeto.
        Pessoa aluno = new Pessoa("Ana");
        Pessoa mesmoAluno = aluno;
        mesmoAluno.nome = "Ana Clara";
        System.out.println("Depois de mesmoAluno.nome = \"Ana Clara\":");
        System.out.println("aluno = " + aluno);
        System.out.println("mesmoAluno = " + mesmoAluno);

        // Reatribuir uma variavel de referencia nao muda o objeto antigo.
        mesmoAluno = new Pessoa("Bruno");
        System.out.println("Depois de mesmoAluno apontar para outro objeto:");
        System.out.println("aluno = " + aluno);
        System.out.println("mesmoAluno = " + mesmoAluno);

        // Parametro objeto: e possivel alterar o objeto apontado pela referencia.
        alterarNome(aluno);
        System.out.println("Depois de alterarNome(aluno): aluno = " + aluno);

        // Reatribuir o parametro nao troca a referencia guardada em aluno.
        substituirPessoa(aluno);
        System.out.println("Depois de substituirPessoa(aluno): aluno = " + aluno);

        Pessoa primeira = new Pessoa("Carlos");
        Pessoa segunda = new Pessoa("Diana");
        tentarTrocarPessoas(primeira, segunda);
        System.out.println("Depois de tentarTrocarPessoas(primeira, segunda):");
        System.out.println("primeira = " + primeira);
        System.out.println("segunda = " + segunda);

        int[] valores = { 1, 2, 3 };
        alterarPrimeiraPosicao(valores);
        System.out.println("Depois de alterarPrimeiraPosicao(valores): valores[0] = " + valores[0]);

        substituirVetor(valores);
        System.out.println("Depois de substituirVetor(valores): valores[0] = " + valores[0]);
    }
}
