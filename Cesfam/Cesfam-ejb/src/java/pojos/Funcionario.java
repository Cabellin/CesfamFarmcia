/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "FUNCIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByRut", query = "SELECT f FROM Funcionario f WHERE f.rut = :rut"),
    @NamedQuery(name = "Funcionario.findByDv", query = "SELECT f FROM Funcionario f WHERE f.dv = :dv"),
    @NamedQuery(name = "Funcionario.findByNombres", query = "SELECT f FROM Funcionario f WHERE f.nombres = :nombres"),
    @NamedQuery(name = "Funcionario.findByApellidoPat", query = "SELECT f FROM Funcionario f WHERE f.apellidoPat = :apellidoPat"),
    @NamedQuery(name = "Funcionario.findByApellidoMat", query = "SELECT f FROM Funcionario f WHERE f.apellidoMat = :apellidoMat")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUT")
    private BigDecimal rut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DV")
    private Character dv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APELLIDO_PAT")
    private String apellidoPat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APELLIDO_MAT")
    private String apellidoMat;
    @JoinColumn(name = "TIPO_FUNC_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoFunc tipoFuncId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionarioRut")
    private Usuario usuario;

    public Funcionario() {
    }

    public Funcionario(BigDecimal rut) {
        this.rut = rut;
    }

    public Funcionario(BigDecimal rut, Character dv, String nombres, String apellidoPat, String apellidoMat) {
        this.rut = rut;
        this.dv = dv;
        this.nombres = nombres;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
    }

    public BigDecimal getRut() {
        return rut;
    }

    public void setRut(BigDecimal rut) {
        this.rut = rut;
    }

    public Character getDv() {
        return dv;
    }

    public void setDv(Character dv) {
        this.dv = dv;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public TipoFunc getTipoFuncId() {
        return tipoFuncId;
    }

    public void setTipoFuncId(TipoFunc tipoFuncId) {
        this.tipoFuncId = tipoFuncId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rut != null ? rut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.rut == null && other.rut != null) || (this.rut != null && !this.rut.equals(other.rut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Funcionario[ rut=" + rut + " ]";
    }
    
}
