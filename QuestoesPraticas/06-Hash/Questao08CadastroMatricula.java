/**
 * Questao 08 - Cadastro por Matricula.
 *
 * Usa uma tabela hash para cadastrar alunos por matricula.
 */
public class Questao08CadastroMatricula {

    private static class Aluno {
        int matricula;
        String nome;

        Aluno(int matricula, String nome) {
            this.matricula = matricula;
            this.nome = nome;
        }

        @Override
        public String toString() {
            return matricula + " - " + nome;
        }
    }

    private static class Nodo<K, V> {
        K chave;
        V valor;
        Nodo<K, V> proximo;

        Nodo(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private static class TabelaHash<K, V> {
        private Nodo<K, V>[] tabela;
        private int nElementos;

        @SuppressWarnings("unchecked")
        TabelaHash(int capacidade) {
            if (capacidade <= 0) {
                throw new IllegalArgumentException("A capacidade deve ser positiva.");
            }
            tabela = (Nodo<K, V>[]) new Nodo<?, ?>[capacidade];
        }

        int tamanho() {
            return nElementos;
        }

        double fatorDeCarga() {
            return (double) nElementos / tabela.length;
        }

        private int hash(K chave) {
            if (chave == null) {
                throw new IllegalArgumentException("A chave nao pode ser null.");
            }
            return Math.floorMod(chave.hashCode(), tabela.length);
        }

        void inserir(K chave, V valor) {
            int indice = hash(chave);
            Nodo<K, V> atual = tabela[indice];

            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    atual.valor = valor;
                    return;
                }
                atual = atual.proximo;
            }

            Nodo<K, V> novo = new Nodo<>(chave, valor);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
            nElementos++;
        }

        V buscar(K chave) {
            Nodo<K, V> atual = tabela[hash(chave)];
            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    return atual.valor;
                }
                atual = atual.proximo;
            }
            return null;
        }

        boolean remover(K chave) {
            int indice = hash(chave);
            Nodo<K, V> atual = tabela[indice];
            Nodo<K, V> anterior = null;

            while (atual != null) {
                if (atual.chave.equals(chave)) {
                    if (anterior == null) {
                        tabela[indice] = atual.proximo;
                    } else {
                        anterior.proximo = atual.proximo;
                    }
                    nElementos--;
                    return true;
                }
                anterior = atual;
                atual = atual.proximo;
            }
            return false;
        }
    }

    private static class CadastroAlunos {
        private TabelaHash<Integer, Aluno> alunos;

        CadastroAlunos(int capacidade) {
            alunos = new TabelaHash<>(capacidade);
        }

        void inserir(int matricula, String nome) {
            // A matricula e a chave; o objeto Aluno e o valor associado.
            alunos.inserir(matricula, new Aluno(matricula, nome));
        }

        String buscar(int matricula) {
            Aluno aluno = alunos.buscar(matricula);
            return aluno == null ? null : aluno.nome;
        }

        boolean remover(int matricula) {
            return alunos.remover(matricula);
        }

        int tamanho() {
            return alunos.tamanho();
        }

        double fatorDeCarga() {
            return alunos.fatorDeCarga();
        }
    }

    public static void main(String[] args) {
        CadastroAlunos cadastro = new CadastroAlunos(10);

        cadastro.inserir(2024001, "Ana");
        cadastro.inserir(2024002, "Bruno");

        System.out.println("Aluno encontrado: " + cadastro.buscar(2024002));
        System.out.println("Aluno 2024001 removido: " + cadastro.remover(2024001));
        System.out.println("Quantidade cadastrada: " + cadastro.tamanho());
        System.out.println("Resultado esperado: Bruno / true");
    }
}
