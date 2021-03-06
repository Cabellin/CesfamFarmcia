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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "COMPUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compuesto.findAll", query = "SELECT c FROM Compuesto c"),
    @NamedQuery(name = "Compuesto.findById", query = "SELECT c FROM Compuesto c WHERE c.id = :id"),
    @NamedQuery(name = "Compuesto.findByDescripcion", query = "SELECT c FROM Compuesto c WHERE c.descripcion = :descripcion")})
public class Compuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compuesto")
    private List<MedicamentoCompuesto> medicamentoCompuestoList;

    public Compuesto() {
    }

    public Compuesto(BigDecimal id) {
        this.id = id;
    }

    public Compuesto(BigDecimal id, String descripcion) {
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
    public List<MedicamentoCompuesto> getMedicamentoCompuestoList() {
        return medicamentoCompuestoList;
    }

    public void setMedicamentoCompuestoList(List<MedicamentoCompuesto> medicamentoCompuestoList) {
        this.medicamentoCompuestoList = medicamentoCompuestoList;
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
        if (!(object instanceof Compuesto)) {
            return false;
        }
        Compuesto other = (Compuesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.Compuesto[ id=" + id + " ]";
    }
    
}
