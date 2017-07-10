/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "RECETA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r"),
    @NamedQuery(name = "Receta.findById", query = "SELECT r FROM Receta r WHERE r.id = :id"),
    @NamedQuery(name = "Receta.findByFecha", query = "SELECT r FROM Receta r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Receta.findByHora", query = "SELECT r FROM Receta r WHERE r.hora = :hora"),
    @NamedQuery(name = "Receta.findByEstado", query = "SELECT r FROM Receta r WHERE r.estado = :estado")})
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
    private List<RecetaMedicamento> recetaMedicamentoList;
    @JoinColumn(name = "PACIENTE_RUT", referencedColumnName = "RUT")
    @ManyToOne(optional = false)
    private Paciente pacienteRut;
    @JoinColumn(name = "USUARIO_NOM_USU", referencedColumnName = "NOM_USU")
    @ManyToOne(optional = false)
    private Usuario usuarioNomUsu;

    public Receta() {
    }

    public Receta(BigDecimal id) {
        this.id = id;
    }

    public Receta(BigDecimal id, Date fecha, Date hora, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<RecetaMedicamento> getRecetaMedicamentoList() {
        return recetaMedicamentoList;
    }

    public void setRecetaMedicamentoList(List<RecetaMedicamento> recetaMedicamentoList) {
        this.recetaMedicamentoList = recetaMedicamentoList;
    }

    public Paciente getPacienteRut() {
        return pacienteRut;
    }

    public void setPacienteRut(Paciente pacienteRut) {
        this.pacienteRut = pacienteRut;
    }

    public Usuario getUsuarioNomUsu() {
        return usuarioNomUsu;
    }

    public void setUsuarioNomUsu(Usuario usuarioNomUsu) {
        this.usuarioNomUsu = usuarioNomUsu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Receta[ id=" + id + " ]";
    }
    
}
