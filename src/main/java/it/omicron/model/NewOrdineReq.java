package it.omicron.model;

import java.io.Serializable;
import java.util.List;

public class NewOrdineReq implements Serializable {

    private static final long serialVersionUID = 11L;


    private List<ArticoliOrdineModel> listaArticoli;

    private String usernameCliente;

    public List<ArticoliOrdineModel> getListaArticoli() {
        return listaArticoli;
    }

    public void setListaArticoli(List<ArticoliOrdineModel> listaArticoli) {
        this.listaArticoli = listaArticoli;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }
}
