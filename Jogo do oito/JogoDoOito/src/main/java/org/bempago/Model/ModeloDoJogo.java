package org.bempago.Model;

import org.bempago.Observer.Observer;

import java.util.*;

public class ModeloDoJogo {
    private static final int TAMANHO = 3;
    private int[][] tabuleiro;
    private int linhaVazia;
    private int colunaVazia;
    private List<Observer> observers;

    public ModeloDoJogo() {
        tabuleiro = new int[TAMANHO][TAMANHO];
        observers = new ArrayList<>();
        reiniciar();


    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notificarObserver() {
        for (Observer observer : observers)
            observer.atualizar();
    }

    public void reiniciar() {
        int num = 1;
        for (int i = 0; i < TAMANHO; i++)
            for (int j = 0; j < TAMANHO; j++) {
                tabuleiro[i][j] = num++;
            }

        tabuleiro[TAMANHO - 1][TAMANHO - 1] = 0;
        linhaVazia = TAMANHO - 1;
        colunaVazia = TAMANHO - 1;
        notificarObserver();
    }



    private void aplicarMovimento(int[][] estadoAnterior, int[][] estadoSeguinte) {
        int linhaVaziaAnterior = -1;
        int colunaVaziaAnterior = -1;
        int linhaVaziaSeguinte = -1;
        int colunaVaziaSeguinte = -1;

        // Encontre as posições da peça vazia nos estados anterior e seguinte
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estadoAnterior[i][j] == 0) {
                    linhaVaziaAnterior = i;
                    colunaVaziaAnterior = j;
                }
                if (estadoSeguinte[i][j] == 0) {
                    linhaVaziaSeguinte = i;
                    colunaVaziaSeguinte = j;
                }
            }
        }

        // Verifique a diferença entre as posições das peças vazias nos estados
        int difLinha = linhaVaziaAnterior - linhaVaziaSeguinte;
        int difColuna = colunaVaziaAnterior - colunaVaziaSeguinte;

        // Determine o movimento a ser aplicado
        int movimento;
        if (difLinha == 1 && difColuna == 0) {
            movimento = 1; // Movimento para cima
        } else if (difLinha == -1 && difColuna == 0) {
            movimento = 2; // Movimento para baixo
        } else if (difLinha == 0 && difColuna == 1) {
            movimento = 3; // Movimento para a esquerda
        } else {
            movimento = 4; // Movimento para a direita
        }

        // Aplicar o movimento trocando as posições das peças
        trocarPecas(estadoAnterior, linhaVaziaAnterior, colunaVaziaAnterior, linhaVaziaSeguinte, colunaVaziaSeguinte, movimento);
    }

    public void aplicarSolucao(int[][] estadoAtual, Map<String, String> paiMap) {
        // Reconstrua o caminho a partir do estado final para o estado inicial usando paiMap
        String chaveEstadoAtual = estadoToString(estadoAtual);
        List<int[][]> caminho = new ArrayList<>();
        caminho.add(estadoAtual);

        while (paiMap.containsKey(chaveEstadoAtual)) {
            String chavePai = paiMap.get(chaveEstadoAtual);
            int[][] estadoPai = stringToEstado(chavePai);
            caminho.add(estadoPai);
            chaveEstadoAtual = chavePai;
        }

        // Inverta o caminho para obter a sequência correta de estados
        Collections.reverse(caminho);

        // Aplique os movimentos do caminho no tabuleiro
        for (int i = 1; i < caminho.size(); i++) {
            int[][] estadoAnterior = caminho.get(i - 1);
            int[][] estadoSeguinte = caminho.get(i);
            aplicarMovimento(estadoAnterior, estadoSeguinte);
            notificarObservers();
        }
    }

    private int[][] stringToEstado(String chave) {
        int[][] estado = new int[TAMANHO][TAMANHO];
        int index = 0;
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                estado[i][j] = chave.charAt(index) - '0';
                index++;
            }
        }
        return estado;
    }

    public List<int[][]> gerarVizinhos(int[][] estado) {
        List<int[][]> vizinhos = new ArrayList();
        int linhaVazia = -1;
        int colunaVazia = -1;

        // Encontre a posição da peça vazia no estado atual
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estado[i][j] == 0) {
                    linhaVazia = i;
                    colunaVazia = j;
                    break;
                }
            }
        }

        // Verifique movimentos válidos e gere estados vizinhos
        int[][] movimentos = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] movimento : movimentos) {
            int novaLinha = linhaVazia + movimento[0];
            int novaColuna = colunaVazia + movimento[1];

            if (novaLinha >= 0 && novaLinha < 3 && novaColuna >= 0 && novaColuna < 3) {
                int[][] novoEstado = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        novoEstado[i][j] = estado[i][j];
                    }
                }
                novoEstado[linhaVazia][colunaVazia] = estado[novaLinha][novaColuna];
                novoEstado[novaLinha][novaColuna] = 0;
                vizinhos.add(novoEstado);
            }
        }

        return vizinhos;
    }

    public boolean resolver() {
        Queue<int[][]> fila = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        Map<String, String> paiMap = new HashMap<>();

        fila.add(tabuleiro);
        visitados.add(estadoToString(tabuleiro));

        while (!fila.isEmpty()) {
            int[][] estadoAtual = fila.poll();
            if (ehSolucao(estadoAtual)) {
                aplicarSolucao(estadoAtual, paiMap);
                return true;
            }

            List<int[][]> vizinhos = gerarVizinhos(estadoAtual);
            for (int[][] vizinho : vizinhos) {
                String chaveVizinho = estadoToString(vizinho);
                if (!visitados.contains(chaveVizinho)) {
                    fila.add(vizinho);
                    visitados.add(chaveVizinho);
                    paiMap.put(chaveVizinho, estadoToString(estadoAtual));
                }
            }
        }

        return false;
    }


    public boolean ehSolucao(int[][] estado) {
        int valorEsperado = 1;

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (estado[i][j] != valorEsperado % 9) {
                    return false;
                }
                valorEsperado++;
            }
        }

        return estado[TAMANHO - 1][TAMANHO - 1] == 0; // A peça vazia deve estar na última posição
    }

    private void aplicarSolucao(int[][] estadoSolucao) {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                tabuleiro[i][j] = estadoSolucao[i][j];
                if (estadoSolucao[i][j] == 0) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }
        notificarObservers();
    }

    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.atualizar();
        }
    }


   /* public void trocarPecas(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        int temp = tabuleiro[linhaOrigem][colunaOrigem];
        tabuleiro[linhaOrigem][colunaOrigem] = tabuleiro[linhaDestino][colunaDestino];
        tabuleiro[linhaDestino][colunaDestino] = temp;
        linhaVazia = linhaDestino;
        colunaVazia = colunaDestino;
        notificarObservers(); // Notificar observadores sobre a mudança no tabuleiro
    }*/

    private int[][] copiarTabuleiro(int[][] original) {
        int[][] copia = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, 3);
        }
        return copia;
    }

    public boolean moverPeca(int linha, int coluna) {

        if (Math.abs(linha - linhaVazia) + Math.abs(coluna - colunaVazia) != 1)
            return false;

        tabuleiro[linhaVazia][colunaVazia] = tabuleiro[linha][coluna];
        tabuleiro[linha][coluna] = 0;
        linhaVazia = linha;
        colunaVazia = coluna;
        notificarObserver();


        return true;
    }

   /*public void trocarPecas(int[][] tabuleiro, int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        int pecaOrigem = tabuleiro[linhaOrigem][colunaOrigem];
        int pecaDestino = tabuleiro[linhaDestino][colunaDestino];

        tabuleiro[linhaOrigem][colunaOrigem] = pecaDestino;
        tabuleiro[linhaDestino][colunaDestino] = pecaOrigem;
    }*/




    public int getValor(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    public boolean verificarVitoria() {
        int num = 1;
        for (int i = 0; i < TAMANHO; i++)
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] != num++ % (TAMANHO * TAMANHO))
                    return false;
            }

        return true;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getLinhaVazia() {
        return linhaVazia;
    }

    public void setLinhaVazia(int linhaVazia) {
        this.linhaVazia = linhaVazia;
    }

    public int getColunaVazia() {
        return colunaVazia;
    }

    public void setColunaVazia(int colunaVazia) {
        this.colunaVazia = colunaVazia;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    private String estadoToString(int[][] estado) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(estado[i][j]);
            }
        }
        return sb.toString();
    }

    public void trocarPecas(int[][] estado, int linhaVaziaAnterior, int colunaVaziaAnterior, int linhaVaziaSeguinte, int colunaVaziaSeguinte, int movimento) {
        // Realize a troca das peças com base no movimento
        switch (movimento) {
            case 1: // Movimento para cima
                estado[linhaVaziaAnterior][colunaVaziaAnterior] = estado[linhaVaziaSeguinte][colunaVaziaSeguinte];
                estado[linhaVaziaSeguinte][colunaVaziaSeguinte] = 0;
                break;
            case 2: // Movimento para baixo
                estado[linhaVaziaAnterior][colunaVaziaAnterior] = estado[linhaVaziaSeguinte][colunaVaziaSeguinte];
                estado[linhaVaziaSeguinte][colunaVaziaSeguinte] = 0;
                break;
            case 3: // Movimento para a esquerda
                estado[linhaVaziaAnterior][colunaVaziaAnterior] = estado[linhaVaziaSeguinte][colunaVaziaSeguinte];
                estado[linhaVaziaSeguinte][colunaVaziaSeguinte] = 0;
                break;
            case 4: // Movimento para a direita
                estado[linhaVaziaAnterior][colunaVaziaAnterior] = estado[linhaVaziaSeguinte][colunaVaziaSeguinte];
                estado[linhaVaziaSeguinte][colunaVaziaSeguinte] = 0;
                break;
            default:
                // Movimento inválido
                break;
        }
    }

    // Resto do código da classe ModeloDoJogo

    public int getTamanho() {
        return TAMANHO;
    }
}
