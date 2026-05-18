/**
 * Questao 08 - Cadastro por Matricula.
 *
 * Usa uma tabela hash simples para cadastrar alunos por matricula.
 */
public class Questao08CadastroMatricula {

    private static class Aluno {
        int matricula;
        String nome;
        Aluno proximo;

        Aluno(int matricula, String nome) {
            this.matricula = matricula;
            this.nome = nome;
        }
    }

    private static class CadastroAlunos {
        private Aluno[] tabela;

        CadastroAlunos(int capacidade) {
            tabela = new Aluno[capacidade];
        }

        private int hash(int matricula) {
            return Math.floorMod(matricula, tabela.length);
        }

        void inserir(int matricula, String nome) {
            int indice = hash(matricula);
            Aluno aluno = buscarNodo(matricula);

            if (aluno != null) {
                aluno.nome = nome;
                return;
            }

            Aluno novo = new Aluno(matricula, nome);
            novo.proximo = tabela[indice];
            tabela[indice] = novo;
        }

        String buscar(int matricula) {
            Aluno aluno = buscarNodo(matricula);
            return aluno == null ? null : aluno.nome;
        }

        private Aluno buscarNodo(int matricula) {
            int indice = hash(matricula);
            Aluno atual = tabela[indice];

            while (atual != null) {
                if (atual.matricula == matricula) {
                    return atual;
                }
                atual = atual.proximo;
            }

            return null;
        }

        boolean remover(int matricula) {
            int indice = hash(matricula);
            Aluno atual = tabela[indice];
            Aluno anterior = null;

            while (atual != null) {
                if (atual.matricula == matricula) {
                    if (anterior == null) {
                        tabela[indice] = atual.proximo;
                    } else {
                        anterior.proximo = atual.proximo;
                    }
                    return true;
                }
                anterior = atual;
                atual = atual.proximo;
            }

            return false;
        }
    }

    public static void main(String[] args) {
        CadastroAlunos cadastro = new CadastroAlunos(10);

        cadastro.inserir(2024001, "Ana");
        cadastro.inserir(2024002, "Bruno");

        System.out.println("Aluno encontrado: " + cadastro.buscar(2024002));
        System.out.println("Aluno 2024001 removido: " + cadastro.remover(2024001));
        System.out.println("Resultado esperado: Bruno / true");
    }
}
