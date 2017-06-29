/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import pojos.Usuario;
import services.UsuarioFacadeLocal;
import pojos.Funcionario;
import services.FuncionarioFacadeLocal;

/**
 *
 * @author Sebastian
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @EJB
    private FuncionarioFacadeLocal funcionarioFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    
    
    private String nomUsu;
    private String contrasena;
    private Usuario usuario;
    boolean loggedIn = false;
    
    private String nombre;
    private String apellidoPat;
    private int rut;
    private Funcionario funcionario;
    
    public UsuarioBean() {
        usuario = new Usuario();
        funcionario = new Funcionario();
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public FuncionarioFacadeLocal getFuncionarioFacade() {
        return funcionarioFacade;
    }

    public void setFuncionarioFacade(FuncionarioFacadeLocal funcionarioFacade) {
        this.funcionarioFacade = funcionarioFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getUsuarios(){
        return usuarioFacade.findAll();
    }
    
    public Usuario getEsteUsuario(){
        return usuarioFacade.find(nomUsu);
    }
    
    public Funcionario buscarEsteFuncionario(){
        return funcionarioFacade.find(rut);
    }
    
     public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        Usuario u = usuarioFacade.find(nomUsu);
        byte[] encoded = Base64.encodeBase64(contrasena.getBytes());   

        if (u != null && contrasena != null && encoded.equals(u.getContrasena()) && u.getFuncionarioRut().getTipoFuncId().getId().intValueExact() == 2) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", u.getFuncionarioRut().getNombres() + " " + u.getFuncionarioRut().getApellidoPat());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("view", "faces/index.xhtml");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Nombre de usuario o clave no válida");
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("view", "faces/logueo.xhtml");
            
        }
    }
     
    public boolean verificarSesionMenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario u1 = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if (u1 == null) {
            return false;
        } else {
            return true;
        }
    }

   
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (u == null) {
                context.getExternalContext().redirect("faces/logueo.xhtml");
            }
        } catch (Exception e) {
            //log
        }
    }

    public void cerrarSesion() {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();

        try {
            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/logueo.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private MenuModel menu;
    
    public MenuModel generarMenu() {
        menu = new DefaultMenuModel();
        Usuario pFarmaceutico = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if (pFarmaceutico != null) {
            this.usuario = pFarmaceutico;
            DefaultMenuItem item = new DefaultMenuItem("INICIO");
            item.setOutcome("index");
            menu.addElement(item);

            item = new DefaultMenuItem("AGREGAR NUEVA PARTIDA");
            item.setOutcome("partida");
            menu.addElement(item);

            item = new DefaultMenuItem("NUEVO MEDICAMENTO");
            item.setOutcome("RegistrarMedicamentos");
            menu.addElement(item);
            
            item = new DefaultMenuItem("MANTENEDOR");
            item.setOutcome("Mantenedor");
            menu.addElement(item);

            item = new DefaultMenuItem("VER RECETAS");
            item.setOutcome("recetas");
            menu.addElement(item);

            item = new DefaultMenuItem("REGISTRAR MERMA");
            item.setOutcome("RegistrarMerma");
            menu.addElement(item);
            
            item = new DefaultMenuItem("ENTREGAR MEDICAMENTOS");
            item.setOutcome("EntregarMedicamentos");
            menu.addElement(item);
            
        }
        return menu;

    }
}
