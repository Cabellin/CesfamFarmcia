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
import java.math.BigInteger;
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
        mermaPendientes = new ArrayList<RegistroMerma>();
        for (RegistroMerma temp : mermas) {
            if (temp.getEstado().equals("Pendiente")) {
                mermaPendientes.add(temp);
            }
        }
        return mermaPendientes;
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
        List<RegistroMerma> mp = new ArrayList<>();
        long pendientes = 0;
        for (RegistroMerma temp : mermaFacade.findAll()) {
            if (temp.getEstado().equals("Pendiente")) {
                if (temp.getMedicamentoCodigo().getCodigo().equals(m.getCodigo())) {
                    mp.add(temp);
                }
            }
        }
        if (!mp.isEmpty()) {
            for (RegistroMerma temp : mp) {
                pendientes = pendientes + temp.getCantidad().longValue();
            }
        }
        int com = merma.getCantidad().compareTo(BigInteger.valueOf(m.getStockFisico().longValue() - pendientes));
        if (com == -1 || com == 0) {
            long diferenciaStock = m.getStockFisico().longValue() - m.getStockDisponible().longValue();
            diferenciaStock = diferenciaStock - pendientes;
            if (diferenciaStock < merma.getCantidad().longValue()) {
                m.setStockDisponible(m.getStockDisponible().subtract(merma.getCantidad().subtract(BigInteger.valueOf(diferenciaStock))));
                medicamentoFacade.edit(m);
            }
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
            descontarStock();
            this.mermaFacade.create(m);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro de merma Agregado exitosamente!!!"));
            merma = new RegistroMerma();
            medicamento = null;
            return "RegistrarMerma";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + e.getMessage(), ""));
            return "RegistrarMerma";
        }
    }

    public String descontarStockFisico(RegistroMerma merma) {
        RegistroMerma m = mermaFacade.find(merma.getIdReg());
        Medicamento med = medicamentoFacade.find(m.getMedicamentoCodigo().getCodigo());
        m.setEstado("Aprobado");
        med.setStockFisico(med.getStockFisico().subtract(m.getCantidad()));
        mermaFacade.edit(m);
        medicamentoFacade.edit(med);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Stock físico descontado!!!"));
        return "descontarStockFisico";
    }

    public String volver() {
        return "RegistrarMerma?faces-redirect=true";
    }

}
