package org.bempago.Factory;

import org.bempago.Controller.ControladorJogo;
import org.bempago.Model.ModeloDoJogo;
import org.bempago.Visao.VisaoDoJogo;

public class JogoDoOitoFactory {

    public ModeloDoJogo criarModelo()
    {
        return new ModeloDoJogo();

    }

    public VisaoDoJogo criarVisao(ModeloDoJogo modelo)
    {
        return new VisaoDoJogo(modelo, this);
    }

    public ControladorJogo criarControlador(ModeloDoJogo modelo, VisaoDoJogo visao)
    {
        return new ControladorJogo(modelo,visao);
    }
}
