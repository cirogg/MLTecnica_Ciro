package com.example.ciro_.mltecnica.Controller;

import com.example.ciro_.mltecnica.Model.Cuotas;
import com.example.ciro_.mltecnica.Model.DAO.DAOCuotas;
import com.example.ciro_.mltecnica.Util.ResultListener;

import java.util.List;

public class CuotasController {

    DAOCuotas daoCuotas;

    public void getCuotas(String monto, String metodoDePagoID, String bancoSeleccionadoID, final ResultListener<List<Cuotas>>escuchadorDeLaVista){
        daoCuotas = new DAOCuotas();
        daoCuotas.getCuotas(monto,metodoDePagoID,bancoSeleccionadoID, new ResultListener<List<Cuotas>>() {
            @Override
            public void finish(List<Cuotas> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

}
