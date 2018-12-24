package com.example.ciro_.mltecnica.Controller;

import com.example.ciro_.mltecnica.Model.DAO.DAOMetodoDePago;
import com.example.ciro_.mltecnica.Model.MetodoDePago;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

public class MetodoDePagoController {

    private DAOMetodoDePago daoMetodoDePago;

    public void getMetodosDePago(final ResultListener<List<MetodoDePago>> escuchadorDeLaVista){
        daoMetodoDePago = new DAOMetodoDePago();
        daoMetodoDePago.getMetodosDePago(new ResultListener<List<MetodoDePago>>() {
            @Override
            public void finish(List<MetodoDePago> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });

    }
}
