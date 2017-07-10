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

/**
 *
 * @author Pelao
 */
@Named(value = "recetaBean")
@ManagedBean
@SessionScoped
public class RecetaBean implements Serializable {

    @EJB
    private PacienteFacadeLocal pacienteFacade;

    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;

    @EJB
    private RecetaFacadeLocal recetaFacade;

    private Receta receta;
    private Paciente paciente;
    private String medicamento;
    private RecetaMedicamento recmed;
    private List<RecetaMedicamento> seleccionados;
    private List<Medicamento> medicamentosBd;
    private List<Receta> recetas;
    private List<Receta> filtro;

    
    public RecetaBean() {
        paciente = new Paciente();
        receta = new Receta();
        seleccionados = new ArrayList<RecetaMedicamento>();
        medicamentosBd = new ArrayList<Medicamento>();
    }

    public RecetaMedicamento getRecmed() {
        return recmed;
    }

    public void setRecmed(RecetaMedicamento recmed) {
        this.recmed = recmed;
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
        return recetaFacade.findAll();
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    } 
    
    public List<RecetaMedicamento> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<RecetaMedicamento> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public List<Medicamento> getMedicamentosBd() {
        return medicamentosBd;
    }

    public void setMedicamentosBd(List<Medicamento> medicamentosBd) {
        this.medicamentosBd = medicamentosBd;
    }
    
    public String verificarRut(){
        Paciente p = pacienteFacade.find(paciente.getRut());
        if (p != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El paciente ha sido encontrado!!"));
            return "EntregarMedicamentos";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"El paciente no ha sido encontrado   qqqqq, vuelva a intentarlo",""));
            return "EntregarMedicamentos";
        }
    }
    
    private BigInteger obtenerId(){
        BigInteger id;
        String idS;
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        idS = ft.format(now);
        id = BigInteger.valueOf(Long.parseLong(idS));
        return id;
    }
    
    public void onDrop(DragDropEvent ddEvent) {
//        Medicamento m = ((Medicamento) ddEvent.getData());
//        receta.setId(BigDecimal.valueOf(obtenerId().longValue()));
//        RecetaMedicamento rm = new RecetaMedicamento(new RecetaMedicamentoPK(medicamento, receta.getId().toBigInteger()), BigInteger.ZERO);
//        rm.setMedicamento(m);
//        
//        seleccionados.add(rm);
//        medicamentosBd.remove(m);
    }
    
    private void descontarStock() throws Exception {
        Medicamento m = medicamentoFacade.find(medicamento);
        int com = recmed.getCantTotal().compareTo(m.getStockFisico());
        if (com == -1 || com == 0) {
            m.setStockDisponible(m.getStockDisponible().subtract(recmed.getCantTotal()));
            medicamentoFacade.edit(m);
        }else{
            throw new Exception("No puede descontar número mayor al Stock Fisico actual");
        }
    }
    
    public String entregarMedicamentos(){
        return " ";
    }
    
    public String verMedicamentosReceta(Receta r){
        receta = r;
        //crear medicamento receta
        return "verMedicamentos?faces-redirect=true"; 
    }
    
    public String cancelar(){
        return "recetas?faces-redirect=true";
    }
    
}