/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "ACCION_FARM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccionFarm.findAll", query = "SELECT a FROM AccionFarm a"),
    @NamedQuery(name = "AccionFarm.findById", query = "SELECT a FROM AccionFarm a WHERE a.id = :id"),
    @NamedQuery(name = "AccionFarm.findByDescripcion", query = "SELECT a FROM AccionFarm a WHERE a.descripcion = :descripcion")})
public class AccionFarm implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinTable(name = "MEDICAMENTO_ACCION", joinColumns = {
        @JoinColumn(name = "ACCION_FARM_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MEDICAMENTO_CODIGO", referencedColumnName = "CODIGO")})
    @ManyToMany
    private List<Medicamento> medicamentoList;

    public AccionFarm() {
    }

    public AccionFarm(BigDecimal id) {
        this.id = id;
    }

    public AccionFarm(BigDecimal id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
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
        if (!(object instanceof AccionFarm)) {
            return false;
        }
        AccionFarm other = (AccionFarm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.AccionFarm[ id=" + id + " ]";
    }
    
}
