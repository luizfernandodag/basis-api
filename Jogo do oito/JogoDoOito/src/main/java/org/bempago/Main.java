package org.bempago;

import org.bempago.Controller.ControladorJogo;
import org.bempago.Factory.JogoDoOitoFactory;
import org.bempago.Model.ModeloDoJogo;
import org.bempago.Visao.VisaoDoJogo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        SwingUtilities.invokeLater(() -> {
            JogoDoOitoFactory factory = new JogoDoOitoFactory();
            ModeloDoJogo modelo = factory.criarModelo();
            VisaoDoJogo visao = factory.criarVisao(modelo);
            ControladorJogo controlador = factory.criarControlador(modelo, visao);
            visao.criarGUI();
        } );
    }
}