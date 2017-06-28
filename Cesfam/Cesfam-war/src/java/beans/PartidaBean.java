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
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;
import pojos.Medicamento;
import pojos.MedicamentoPartida;
import pojos.MedicamentoPartidaPK;
import pojos.NomGenerico;
import pojos.Partida;
import pojos.Proveedor;
import services.MedicamentoFacadeLocal;
import services.NomGenericoFacadeLocal;
import services.PartidaFacadeLocal;
import services.ProveedorFacadeLocal;

/**
 *
 * @author Sebastian
 */
@Named(value = "partidaBean")
@ManagedBean
@SessionScoped
public class PartidaBean implements Serializable {

    @EJB
    private PartidaFacadeLocal partidaFacade;
    
    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;
    
    @EJB
    private ProveedorFacadeLocal proveedorFacade;
    
    @EJB
    private NomGenericoFacadeLocal nomGenFacade;
    
    private Partida partida;
    private List<MedicamentoPartida> seleccionados;
    private List<Medicamento> medicamentosBd;

    public PartidaBean() {
        partida = new Partida();               
        seleccionados = new ArrayList<MedicamentoPartida>();
        medicamentosBd = new ArrayList<Medicamento>();
    }

    public Partida getPartida() {
        return partida;
    }

    public List<Proveedor> getProveedores() {
        return proveedorFacade.findAll();
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public List<Medicamento> getMedicamentosBd() {
        return medicamentosBd;
    }
    
    public String obtenerNombreGenerico(NomGenerico id) {
        return nomGenFacade.find(id).getDescripcion();
    }

    public void setMedicamentosBd(List<Medicamento> medicamentosBd) {
        this.medicamentosBd = medicamentosBd;
    }
    
    public List<MedicamentoPartida> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<MedicamentoPartida> seleccionados) {
        this.seleccionados = seleccionados;
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
        Medicamento m = ((Medicamento) ddEvent.getData());
        partida.setId(BigDecimal.valueOf(obtenerId().longValue()));
        MedicamentoPartida mp = new MedicamentoPartida(new MedicamentoPartidaPK(partida.getId().toBigInteger(), m.getCodigo()), BigInteger.ZERO, new Date());
        mp.setMedicamento(m);
        
        seleccionados.add(mp);
        medicamentosBd.remove(m);
    }
    
    public void quitarMedicamento(MedicamentoPartida mp) {
        medicamentosBd.add(mp.getMedicamento());
        seleccionados.remove(mp);
    }
    
    public String partida(){
        medicamentosBd = medicamentoFacade.findAll();        
        seleccionados = new ArrayList<MedicamentoPartida>();
        return "partida";
    }
    
    private void aumentarStock(){
        for(MedicamentoPartida mp : seleccionados){
            Medicamento m = medicamentoFacade.find(mp.getMedicamento().getCodigo());
            m.setStockFisico(m.getStockFisico().add(mp.getCantidad()));
            m.setStockDisponible(m.getStockDisponible().add(mp.getCantidad()));
            medicamentoFacade.edit(m);
        }
    }
    
    public String crearPartida() {
        try {
            Partida p = new Partida();
            p.setId(partida.getId());
            p.setFechaEntrega(new Date());
            p.setProveedorId(proveedorFacade.find(p.getProveedorId()));
            p.setMedicamentoPartidaList(seleccionados);
            this.partidaFacade.create(p);
            aumentarStock();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Partida ingresada correctamente"));
            seleccionados = new ArrayList<MedicamentoPartida>();
            medicamentosBd = new ArrayList<Medicamento>();
            partida = new Partida();
            return "index";            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe editar los datos de los compuestos", ""));
            return "partida";
        }

    }
}
