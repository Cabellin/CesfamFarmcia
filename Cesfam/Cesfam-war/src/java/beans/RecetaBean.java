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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pojos.Receta;
import services.RecetaFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import org.primefaces.event.DragDropEvent;
import pojos.Medicamento;
import pojos.Paciente;
import pojos.RecetaMedicamento;
import pojos.RecetaMedicamentoPK;
import services.MedicamentoFacadeLocal;
import services.PacienteFacadeLocal;
import services.RecetaMedicamentoFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "recetaBean")
@ManagedBean
@SessionScoped
public class RecetaBean implements Serializable {

    @EJB
    private RecetaMedicamentoFacadeLocal recetaMedicamentoFacade;

    @EJB
    private PacienteFacadeLocal pacienteFacade;

    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;

    @EJB
    private RecetaFacadeLocal recetaFacade;

    private Receta receta;
    private Paciente paciente;
    private String medicamento;
    private List<Medicamento> medicamentosBd;
    private List<RecetaMedicamento> recetaMedList;
    private List<Receta> recetas;
    private List<Receta> filtro;

    public RecetaBean() {
        paciente = new Paciente();
        receta = new Receta();
        medicamentosBd = new ArrayList<Medicamento>();
    }

    public List<RecetaMedicamento> getRecetaMedList() {
        return recetaMedList;
    }

    public void setRecetaMedList(List<RecetaMedicamento> recetaMedList) {
        this.recetaMedList = recetaMedList;
    }

    public List<Receta> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Receta> filtro) {
        this.filtro = filtro;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public List<Receta> getRecetas() {
        obtenerRecetasPendientes();
        return this.recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public List<Medicamento> getMedicamentosBd() {
        return medicamentosBd;
    }

    public void setMedicamentosBd(List<Medicamento> medicamentosBd) {
        this.medicamentosBd = medicamentosBd;
    }

    public List<String> getEstadosMedicamento() {
        List<String> estados = new ArrayList<>();
        estados.add("Entregado");
        estados.add("Reservado");
        estados.add("No aplica");
        estados.add("Permanente");
        return estados;
    }

    private void descontarStock() throws Exception {
        for (RecetaMedicamento recmed : receta.getRecetaMedicamentoList()) {
            Medicamento m = recmed.getMedicamento();
            int com = recmed.getCantTotal().compareTo(m.getStockFisico());
            if (recmed.getEstado().equals("Entregado")) {
                if (com == -1 || com == 0) {
                    m.setStockDisponible(m.getStockDisponible().subtract(recmed.getCantTotal()));
                    medicamentoFacade.edit(m);
                } else {
                    throw new Exception("No puede descontar número mayor al Stock Fisico actual");
                }
            }
        }
    }

    public String verMedicamentosReceta(Receta r) {
        receta = r;
        recetaMedList = receta.getRecetaMedicamentoList();
        return "verMedicamentos?faces-redirect=true";
    }

    public String cancelar() {
        return "recetas?faces-redirect=true";
    }

    public String entregarTodos() {
        for (RecetaMedicamento temp : recetaMedList) {
            temp.setEstado("Entregado");
        }
        return "verMedicamentos";
    }

    private void obtenerRecetasPendientes(){
        recetas = new ArrayList<>();
        List<Receta> todasRecetas = recetaFacade.findAll();
        for (Receta temp : todasRecetas) {
            if (temp.getEstado().equals("Pendiente")) {
                recetas.add(temp);
            }            
        }
    }
    
    private void verificarPendientes() throws Exception {
        for (RecetaMedicamento temp : recetaMedList) {
            if (temp.getEstado().equals("Pendiente")) {
                throw new Exception("Tiene medicamentos pendientes");
            }
        }
    }

    public String confirmarSi() {
        for (RecetaMedicamento temp : recetaMedList) {
            if (temp.getEstado().equals("Entregado") && temp.getCantTotal().longValue() > temp.getMedicamento().getStockDisponible().longValue()) {
                temp.setEstado("Reservado");
            }
        }
        return "verMedicamentos";
    }

    public String confirmarNo() {
        return "verMedicamentos";
    }

    private void cambiarEstadoReceta() {
        OUTER:
        for (RecetaMedicamento temp : recetaMedList) {
            switch (temp.getEstado()) {
                case "Reservado":
                    receta.setEstado("Reservado");
                    return;
                case "Permanente":
                    receta.setEstado("Permanente");
                    return;
                default:
                    receta.setEstado("Listo");
                    break;
            }
        }
    }

    public void verificarStock() throws Exception {
        for (RecetaMedicamento temp : recetaMedList) {
            if (temp.getEstado().equals("Entregado") && temp.getCantTotal().longValue() > temp.getMedicamento().getStockDisponible().longValue()) {
                throw new Exception("Tiene medicamentos sin stock suficiente, puede reservar");
            }
        }
    }

    public String confirmarReceta() {
        try {
            verificarPendientes();
            verificarStock();
            cambiarEstadoReceta();
            receta.setRecetaMedicamentoList(recetaMedList);
            recetaFacade.edit(receta);
            descontarStock();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Receta confirmada exitosamente!!!"));
            return "recetas?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + e.getMessage(), ""));
            return "verMedicamentos";
        }
    }
}
