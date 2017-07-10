/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pojos.Medicamento;
import pojos.RegistroMerma;
import services.MedicamentoFacadeLocal;
import services.RegistroMermaFacadeLocal;

/**
 *
 * @author Sebastian
 */
@Named(value = "mermaBean")
@ManagedBean
@SessionScoped
public class MermaBean implements Serializable {

    @EJB
    private RegistroMermaFacadeLocal mermaFacade;

    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;

    private RegistroMerma merma;
    private String medicamento;
    private List<Medicamento> medicamentos;
    private List<RegistroMerma> mermaPendientes;

    public MermaBean() {
        merma = new RegistroMerma();
    }

    public RegistroMerma getMerma() {
        return merma;
    }

    public void setMerma(RegistroMerma merma) {
        this.merma = merma;
    }

    public List<RegistroMerma> getMermaPendientes() {
        List<RegistroMerma> mermas = mermaFacade.findAll();
        List<RegistroMerma> pendientes = new ArrayList<RegistroMerma>();
        for (RegistroMerma temp : mermas) {
            if (temp.getEstado().equals("Pendiente")) {
                pendientes.add(temp);
            }
        }
        return pendientes;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public List<Medicamento> getMedicamentos() {
        medicamentos = medicamentoFacade.findAll();
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<RegistroMerma> getMermas() {
        return mermaFacade.findAll();
    }

    public List<String> motivos() {
        List<String> m = new ArrayList<String>();
        m.add("Vencimiento");
        m.add("Caja Rota");
        m.add("Mal Estado");
        m.add("Perdido");
        m.add("Otros");
        return m;
    }

    private void descontarStock() throws Exception {
        Medicamento m = medicamentoFacade.find(medicamento);
        int com = merma.getCantidad().compareTo(m.getStockFisico());
        if (com == -1 || com == 0) {
            m.setStockDisponible(m.getStockDisponible().subtract(merma.getCantidad()));
            medicamentoFacade.edit(m);
        } else {
            throw new Exception("No puede descontar número mayor al Stock Fisico actual");
        }
    }

    public String registrarMerma() {
        try {
            RegistroMerma m = new RegistroMerma();
            m.setCantidad(merma.getCantidad());
            m.setDescripcion(merma.getDescripcion());
            m.setMotivo(merma.getMotivo());
            m.setFecha(new Date());
            m.setHora(new Date());
            m.setEstado("Pendiente");
            m.setIdReg(BigDecimal.ZERO);
            m.setMedicamentoCodigo(medicamentoFacade.find(medicamento));
            this.mermaFacade.create(m);
            descontarStock();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro de merma Agregado exitosamente!!!"));
            merma = new RegistroMerma();
            medicamento = null;
            return "RegistrarMerma";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error, vuelva a intentarlo ".concat(e.getMessage())));
            return "RegistrarMerma";
        }
    }

    public String descontarStockFisico(RegistroMerma merma) {
        RegistroMerma m = mermaFacade.find(merma.getIdReg());
        m.setEstado("Aprobado");
        mermaFacade.edit(m);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Stock físico descontado!!!"));
        return "descontarStockFisico";
    }

    public String volver(){
        return "RegistrarMerma?faces-redirect=true";
    }
    
}
