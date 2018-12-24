package com.example.ciro_.mltecnica.Controller;

import com.example.ciro_.mltecnica.Model.Banco;
import com.example.ciro_.mltecnica.Model.DAO.DAOBanco;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

public class BancoController {

    DAOBanco daoBanco;

    public void getBancos(final ResultListener<List<Banco>> escuchadorDeLaVista, String metodoDePagoID){
        daoBanco = new DAOBanco();
        daoBanco.getBancos(new ResultListener<List<Banco>>() {
            @Override
            public void finish(List<Banco> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        },metodoDePagoID);
    }
}
