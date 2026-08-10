import Unidade2.P02Pilhas.PilhaDinamica;

/**
 * Questão 06 - Histórico de navegação.
 *
 * Duas pilhas representam as páginas disponíveis para voltar e avançar.
 */
public class Questao06HistoricoNavegacao {

    /**
     * Histórico pequeno com as operações comuns de um navegador.
     */
    public static class HistoricoNavegacao {
        private final PilhaDinamica<String> anteriores = new PilhaDinamica<>();
        private final PilhaDinamica<String> seguintes = new PilhaDinamica<>();
        private String paginaAtual;

        public HistoricoNavegacao(String paginaInicial) {
            paginaAtual = paginaInicial;
        }

        public String paginaAtual() {
            return paginaAtual;
        }

        public void visitar(String pagina) {
            anteriores.empilhar(paginaAtual);
            paginaAtual = pagina;

            // Uma nova visita apaga o histórico de avançar.
            while (!seguintes.estaVazia()) {
                seguintes.desempilhar();
            }
        }

        public void voltar() {
            if (!anteriores.estaVazia()) {
                seguintes.empilhar(paginaAtual);
                paginaAtual = anteriores.desempilhar();
            }
        }

        public void avancar() {
            if (!seguintes.estaVazia()) {
                anteriores.empilhar(paginaAtual);
                paginaAtual = seguintes.desempilhar();
            }
        }
    }

    public static void main(String[] args) {
        HistoricoNavegacao historico = new HistoricoNavegacao("inicio");
        historico.visitar("aulas");
        historico.visitar("pilhas");
        historico.voltar();
        System.out.println("Depois de voltar: " + historico.paginaAtual());
        historico.avancar();
        System.out.println("Depois de avançar: " + historico.paginaAtual());
        System.out.println("Resultados esperados: aulas e pilhas");

        // Experimente voltar e depois visitar uma página nova.
    }
}
