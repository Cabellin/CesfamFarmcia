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
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pojos.Laboratorio;
import services.LaboratorioFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Sebastian
 */
@Named(value = "laboratorioBean")
@ManagedBean
@SessionScoped
public class LaboratorioBean implements Serializable {

    @EJB
    private LaboratorioFacadeLocal laboratorioFacade;

    private Laboratorio laboratorio;
    private List<Laboratorio> laboratorios;

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public LaboratorioBean() {
        laboratorio = new Laboratorio();
    }

    public List<Laboratorio> getLaboratorios() {
        laboratorios = laboratorioFacade.findAll();
        return laboratorios;
    }

    public String nuevoLaboratorio() {
        try {
            Laboratorio l = new Laboratorio();
            l.setId(BigDecimal.ONE);
            l.setDescripcion(laboratorio.getDescripcion());
            this.laboratorioFacade.create(l);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Laboratorio Agregado exitosamente!!!"));
            laboratorio = new Laboratorio();
            return "PasoDos";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error, vuelva a intentarlo"));
            return "PasoDos";
        }
    }

}
