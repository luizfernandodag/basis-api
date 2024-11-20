package org.bempago.Visao;

import org.bempago.Factory.JogoDoOitoFactory;
import org.bempago.Model.ModeloDoJogo;

import org.bempago.Observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisaoDoJogo {
    private ModeloDoJogo modelo;
    private JFrame frame;
    private JButton[][] botoes;
    private JButton resolverButton;
    private JButton resetarButton;

    public VisaoDoJogo(ModeloDoJogo modelo, JogoDoOitoFactory factory)
    {
        this.modelo = modelo;
        modelo.addObserver((new ModeloObserver()));

        resolverButton = new JButton("Resolver");
        resolverButton.addActionListener(e -> modelo.resolver());

        resetarButton = new JButton("Resetar");
        resetarButton.addActionListener(e -> modelo.reiniciar());
    }

    public void criarGUI()
    {

        int tamanho = modelo.getTamanho();
        frame = new JFrame("Jogo do Oito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel botoesPanel = new JPanel(new GridLayout(tamanho, tamanho));
        botoes = new JButton[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int valor = modelo.getValor(i, j);
                JButton botao = new JButton(valor == 0 ? "" : Integer.toString(valor));
                botao.addActionListener(new BotaoClickListener(i, j));
                botoes[i][j] = botao;
                botoesPanel.add(botao);
            }
        }

        frame.add(botoesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(resolverButton);
        buttonPanel.add(resetarButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(300, 350);
        frame.setVisible(true);


    }

    public void atualizarBotoes()
    {
        int tamanho = modelo.getTamanho();

        for (int i = 0; i<tamanho;i++) {
            for (int j = 0; j < tamanho; j++) {
                int valor = modelo.getValor(i,j);
                botoes[i][j].setText(valor==0? "": Integer.toString(valor));
            }
        }
    }

    public void mostrarMensagemVitoria()
    {
        JOptionPane.showMessageDialog(frame,"Parabéns! Você ganhou!");
    }


    private class BotaoClickListener implements ActionListener {
        private int linha;
        private int coluna;


        public BotaoClickListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(modelo.moverPeca(linha, coluna))
            {
                atualizarBotoes();
                if(modelo.verificarVitoria())
                {
                    mostrarMensagemVitoria();
                }

            }
        }
    }

    private class ModeloObserver implements Observer {

        @Override
        public void atualizar() {
            atualizarBotoes();
        }

    }




}
