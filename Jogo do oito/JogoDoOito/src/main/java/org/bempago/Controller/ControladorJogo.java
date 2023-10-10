package org.bempago.Controller;

import org.bempago.Model.ModeloDoJogo;
import org.bempago.Visao.VisaoDoJogo;

public class ControladorJogo {

    private ModeloDoJogo modelo;
    private VisaoDoJogo visao;

    public ControladorJogo(ModeloDoJogo modelo, VisaoDoJogo visao) {
        this.modelo = modelo;
        this.visao = visao;
    }
}
