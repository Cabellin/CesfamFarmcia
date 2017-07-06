/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import java.io.Serializable;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.DragDropEvent;
import pojos.AccionFarm;
import pojos.Compuesto;
import pojos.Laboratorio;
import pojos.Medicamento;
import pojos.MedicamentoCompuesto;
import pojos.MedicamentoCompuestoPK;
import pojos.NomGenerico;
import pojos.Presentacion;
import pojos.ViaAdministracion;
import services.AccionFarmFacadeLocal;
import services.CompuestoFacadeLocal;
import services.LaboratorioFacadeLocal;
import services.MedicamentoFacadeLocal;
import services.NomGenericoFacadeLocal;
import services.PresentacionFacadeLocal;
import services.ViaAdministracionFacadeLocal;

/**
 *
 * @author Pelao
 */
@Named(value = "medicamentoBean")
@ManagedBean
@SessionScoped
public class MedicamentoBean implements Serializable {

    @EJB
    private AccionFarmFacadeLocal accionFarmFacade;

    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;

    @EJB
    private LaboratorioFacadeLocal laboratorioFacade;

    @EJB
    private CompuestoFacadeLocal compuestoFacade;

    @EJB
    private ViaAdministracionFacadeLocal viaAdmFacade;

    @EJB
    private NomGenericoFacadeLocal nomGenFacade;

    @EJB
    private PresentacionFacadeLocal presentacionFacade;

    private List<MedicamentoCompuesto> seleccionados;
    private Medicamento medicamento;
    private List<Compuesto> compuestosBd;
    private List<Medicamento> filtrados;
    private BigDecimal nomGId;
    private BigDecimal viaAdmId;
    private BigDecimal presId;
    private BigDecimal labId;
    private List<String> accionFarmListId;
    private List<Compuesto> compFiltrados;

    public List<Compuesto> getCompFiltrados() {
        return compFiltrados;
    }

    public void setCompFiltrados(List<Compuesto> compFiltrados) {
        this.compFiltrados = compFiltrados;
    }

    public List<Medicamento> getFiltrados() {
        return filtrados;
    }

    public List<String> getAccionFarmListId() {
        return accionFarmListId;
    }

    public void setAccionFarmListId(List<String> accionFarmList) {
        this.accionFarmListId = accionFarmList;
    }

    public BigDecimal getLabId() {
        return labId;
    }

    public void setLabId(BigDecimal labId) {
        this.labId = labId;
    }

    public BigDecimal getViaAdmId() {
        return viaAdmId;
    }

    public void setViaAdmId(BigDecimal viaAdmId) {
        this.viaAdmId = viaAdmId;
    }

    public BigDecimal getPresId() {
        return presId;
    }

    public void setPresId(BigDecimal presId) {
        this.presId = presId;
    }

    public BigDecimal getNomGId() {
        return nomGId;
    }

    public void setNomGId(BigDecimal nomGId) {
        this.nomGId = nomGId;
    }

    public void setFiltrados(List<Medicamento> filtrados) {
        this.filtrados = filtrados;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public List<Compuesto> getCompuestosBd() {
        return compuestosBd;
    }

    public List<MedicamentoCompuesto> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<MedicamentoCompuesto> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public List<Presentacion> getPresentaciones() {
        return presentacionFacade.findAll();
    }

    public List<ViaAdministracion> getViasAdm() {
        return viaAdmFacade.findAll();
    }

    public List<AccionFarm> getAcciones() {
        return accionFarmFacade.findAll();
    }

    public List<NomGenerico> getNombresGen() {
        return nomGenFacade.findAll();
    }

    public List<Laboratorio> getLaboratorios() {
        return laboratorioFacade.findAll();
    }

    public MedicamentoBean() {
        medicamento = new Medicamento();
        seleccionados = new ArrayList<MedicamentoCompuesto>();
        compuestosBd = new ArrayList<Compuesto>();
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentoFacade.findAll();
    }

    private List<AccionFarm> ObtenerAccionesSelec(List<String> accionesId) {
        List<AccionFarm> accionesSelec = new ArrayList<>();
        AccionFarm ac = new AccionFarm();
        for (String temp : accionesId) {
            BigDecimal id = new BigDecimal(temp);
            ac = accionFarmFacade.find(id);
            ac.getMedicamentoList().add(medicamentoFacade.find(medicamento.getCodigo()));
            accionFarmFacade.edit(ac);
            accionesSelec.add(ac);
        }
        return accionesSelec;
    }

    public void verificarUnidad() throws Exception {
        for (MedicamentoCompuesto temp : seleccionados) {
            if (temp.getUnidad().equals("seleccione") || temp.getUnidad() == null || temp.getUnidad().equals("")) {
                throw new Exception("Verificar unidad de compuestos");
            }
        }
    }

    public void verificarCantidad() throws Exception {
        for (MedicamentoCompuesto temp : seleccionados) {
            if (temp.getCantidad() == 0) {
                throw new Exception("Verificar cantidad de compuestos");
            }
        }
    }

    public void verificarCompuestos() throws Exception {
        if (seleccionados.isEmpty()) {
            throw new Exception("Debe seleccionar compuestos");
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda Actualizada", "Antes: " + oldValue + ", Ahora:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

//    public String pasoDos() {
//        medicamento.setContenido(medicamento.getContenido().concat(" " + unidadMedida));
//        compuestosBd = compuestoFacade.findAll();
//        seleccionados = new ArrayList<MedicamentoCompuesto>();
//        return "PasoDos";
//    }
    public String pasoDos() {
        Medicamento m = medicamentoFacade.find(medicamento.getCodigo());
        if (m == null) {
            compuestosBd = compuestoFacade.findAll();
            seleccionados = new ArrayList<MedicamentoCompuesto>();
            return "PasoDos";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El código ya existe en nuestra base de datos, vuelva a intentarlo", ""));
            return "RegistrarMedicamentos";
        }
    }

    public String volverRegistrar() {
        return "RegistrarMedicamentos";
    }

    public String volverPartida() {
        medicamento = new Medicamento();
        return "partida";
    }

    public List<String> getUnidadesMedida() {
        List<String> u = new ArrayList<String>();
        u.add("mg");
        u.add("gr");
        u.add("ml");
        u.add("lt");
        u.add("ui");
        return u;
    }

    public List<String> getUnidadesMedida2() {
        List<String> u = new ArrayList<String>();
        u.add("seleccione");
        u.add("mg");
        u.add("gr");
        u.add("ml");
        u.add("lt");
        u.add("ui");
        return u;
    }

    public void onDrop(DragDropEvent ddEvent) {
        Compuesto c = ((Compuesto) ddEvent.getData());
        MedicamentoCompuesto cm = new MedicamentoCompuesto(new MedicamentoCompuestoPK(c.getId().toBigInteger(), medicamento.getCodigo()), 0, "");
        cm.setCompuesto(c);

        seleccionados.add(cm);
        compuestosBd.remove(c);
    }

    public void quitarCompuesto(MedicamentoCompuesto mc) {
        compuestosBd.add(mc.getCompuesto());
        seleccionados.remove(mc);
    }

    public String crearMedicamento() {
        try {
            verificarCompuestos();
            verificarCantidad();
            verificarUnidad();
            Medicamento m = new Medicamento();
            m.setCodigo(medicamento.getCodigo());
            m.setLaboratorioId(laboratorioFacade.find(labId));
            m.setNomGenericoId(nomGenFacade.find(nomGId));
            m.setNomComercial(medicamento.getNomComercial());
            m.setContenido(medicamento.getContenido());
            m.setUnidadCont(medicamento.getUnidadCont());
            m.setViaAdministracionId(viaAdmFacade.find(viaAdmId));
            m.setPresentacionId(presentacionFacade.find(presId));
            m.setUPorCaja(medicamento.getUPorCaja());
            m.setStockDisponible(BigInteger.ZERO);
            m.setStockFisico(BigInteger.ZERO);
            m.setMedicamentoCompuestoList(seleccionados);
            this.medicamentoFacade.create(m);
            m.setAccionFarmList(ObtenerAccionesSelec(accionFarmListId));
            medicamentoFacade.edit(m);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Medicamento Agregado exitosamente!!!"));
            Limpiar();
            return "RegistrarMedicamentos?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + e.getMessage(), ""));
            return "PasoDos";
        }
    }

    private void Limpiar() {
        medicamento = new Medicamento();
        seleccionados = new ArrayList<>();
        nomGId = BigDecimal.ZERO;
        viaAdmId = BigDecimal.ZERO;
        presId = BigDecimal.ZERO;
        labId = BigDecimal.ZERO;
        accionFarmListId = new ArrayList<>();
    }

    public String editarMedicamentoCompuesto() {
//        seleccionados = mc;
        return "cambiarCompuestos?faces-redirect=true";
    }

    public String eliminarMedicamento(Medicamento medicamento) {
        Medicamento m = medicamentoFacade.find(medicamento.getCodigo());
        medicamentoFacade.remove(m);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Medicamento Eliminado!!!"));
        return "Mantenedor";
    }

//    public String actualizarMedicamento() {
//        Medicamento m = medicamentoFacade.find(medicamento.getCodigo());
//        m.setCodigo(medicamento.getCodigo());
//        m.setNomGenericoId(nomGenFacade.find(nomGen.getId()));
//        m.setNomComercial(medicamento.getNomComercial());
//        m.setViaAdministracionId(viaAdmFacade.find(viaAdm.getId()));
//        m.setPresentacionId(presentacionFacade.find(presentacion.getId()));
//        m.setContenido(medicamento.getContenido());
//        m.setUPorCaja(medicamento.getUPorCaja());
//        m.setStockDisponible(medicamento.getStockDisponible());
//        m.setStockFisico(medicamento.getStockFisico());
//        medicamentoFacade.edit(m);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Medicamento actualizada!!!"));
//        return "Mantenedor";
//    }
}
