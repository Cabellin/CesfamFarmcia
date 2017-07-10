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
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;
import pojos.Medicamento;
import pojos.MedicamentoPartida;
import pojos.MedicamentoPartidaPK;
import pojos.NomGenerico;
import pojos.Paciente;
import pojos.Partida;
import pojos.Proveedor;
import pojos.Receta;
import services.MedicamentoFacadeLocal;
import services.NomGenericoFacadeLocal;
import services.PacienteFacadeLocal;
import services.PartidaFacadeLocal;
import services.ProveedorFacadeLocal;
import services.RecetaFacadeLocal;

/**
 *
 * @author Sebastian
 */
@Named(value = "partidaBean")
@ManagedBean
@SessionScoped
public class PartidaBean implements Serializable {

    @EJB
    private PacienteFacadeLocal pacienteFacade;

    @EJB
    private PartidaFacadeLocal partidaFacade;

    @EJB
    private MedicamentoFacadeLocal medicamentoFacade;

    @EJB
    private ProveedorFacadeLocal proveedorFacade;

    @EJB
    private NomGenericoFacadeLocal nomGenFacade;
    
    @EJB
    private RecetaFacadeLocal recetaFacade;

    private Partida partida;
    private List<MedicamentoPartida> seleccionados;
    private List<Medicamento> medicamentosBd;
    private BigDecimal proveedorId;

    public PartidaBean() {
        partida = new Partida();
    }

    public BigDecimal getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(BigDecimal proveedorId) {
        this.proveedorId = proveedorId;
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
        return this.medicamentosBd;
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

    private BigInteger obtenerId() {
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
        MedicamentoPartida mp = new MedicamentoPartida(new MedicamentoPartidaPK(partida.getId().toBigInteger(), m.getCodigo()), BigInteger.ZERO, null);
        mp.setMedicamento(m);

        seleccionados.add(mp);
        medicamentosBd.remove(m);
    }

    public void quitarMedicamento(MedicamentoPartida mp) {
        medicamentosBd.add(mp.getMedicamento());
        seleccionados.remove(mp);
    }

    public String partidaComienzo() {
        if (medicamentosBd == null || seleccionados == null) {
            medicamentosBd = medicamentoFacade.findAll();
            seleccionados = new ArrayList<MedicamentoPartida>();
        }
        return "partida";
    }

    private void aumentarStock() {
        for (MedicamentoPartida mp : seleccionados) {
            Medicamento m = medicamentoFacade.find(mp.getMedicamento().getCodigo());
            m.setStockFisico(m.getStockFisico().add(mp.getCantidad()));
            m.setStockDisponible(m.getStockDisponible().add(mp.getCantidad()));
            medicamentoFacade.edit(m);
        }
    }

    private void validarCantidad() throws Exception {
        for (MedicamentoPartida mp : seleccionados) {
            if (mp.getCantidad().intValue() == 0 || mp.getCantidad() == null) {
                throw new Exception("Verificar cantidad en medicamentos");
            }
        }
    }

    private void validarFechaVenc() throws Exception {
        for (MedicamentoPartida mp : seleccionados) {
            if (mp.getFechaVencimiento() == null) {
                throw new Exception("Verificar fecha de vencimiento en medicamentos");
            }
        }
    }

    public void verificarMedicamentos() throws Exception {
        if (seleccionados.isEmpty()) {
            throw new Exception("Debe seleccionar medicamentos");
        }
    }

    private void notificarPendientes() {
        List<Receta> recetas = recetaFacade.findAll();
        List<Receta> recetasPendientes = new ArrayList<>();
        for (Receta temp : recetas) {
            if (temp.getEstado().equals("Pendiente")) {
                recetasPendientes.add(temp);
            }
        }
        // falta
    }

    public String crearPartida() {
        try {
            validarCantidad();
            validarFechaVenc();
            Partida p = new Partida();
            p.setId(partida.getId());
            p.setFechaEntrega(new Date());
            p.setProveedorId(proveedorFacade.find(proveedorId));
            p.setMedicamentoPartidaList(seleccionados);
            this.partidaFacade.create(p);
            aumentarStock();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Partida ingresada correctamente"));
            seleccionados = new ArrayList<MedicamentoPartida>();
            medicamentosBd = new ArrayList<Medicamento>();
            partida = new Partida();
            return "index";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + e.getMessage(), ""));
            return "partida";
        }
    }
    
    public void informarPaciente(Paciente paciente){
        Paciente p = pacienteFacade.find(paciente.getRut());
        
        final String username = "soportecesfamquilpue@gmail.com";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(p.getCorreo()));
            message.setSubject("Su medicamento ha llegado a nuestra farmacia");
            message.setText("Estimado " + p.getNombres()+ " " + p.getApellidoPat()+ "\n"
                    + "Se le comunica que el medicamento que se encuentra reservado a su nombre ya tiene stock, se le solicita retirarlo en el mezón de atención al paciente de nuestra farmacia a la brevedad. " + "\n" + "\n"
                    + "Atte" + "\n"
                    + "Farmacia CESFAM");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
    }
}
