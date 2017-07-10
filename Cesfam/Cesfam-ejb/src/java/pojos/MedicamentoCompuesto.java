/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "MEDICAMENTO_COMPUESTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentoCompuesto.findAll", query = "SELECT m FROM MedicamentoCompuesto m"),
    @NamedQuery(name = "MedicamentoCompuesto.findByCompuestoId", query = "SELECT m FROM MedicamentoCompuesto m WHERE m.medicamentoCompuestoPK.compuestoId = :compuestoId"),
    @NamedQuery(name = "MedicamentoCompuesto.findByMedicamentoCodigo", query = "SELECT m FROM MedicamentoCompuesto m WHERE m.medicamentoCompuestoPK.medicamentoCodigo = :medicamentoCodigo"),
    @NamedQuery(name = "MedicamentoCompuesto.findByCantidad", query = "SELECT m FROM MedicamentoCompuesto m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MedicamentoCompuesto.findByUnidad", query = "SELECT m FROM MedicamentoCompuesto m WHERE m.unidad = :unidad"),
    @NamedQuery(name = "MedicamentoCompuesto.findByDescripcion", query = "SELECT m FROM MedicamentoCompuesto m WHERE m.descripcion = :descripcion")})
public class MedicamentoCompuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicamentoCompuestoPK medicamentoCompuestoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private double cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UNIDAD")
    private String unidad;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "COMPUESTO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Compuesto compuesto;
    @JoinColumn(name = "MEDICAMENTO_CODIGO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Medicamento medicamento;

    public MedicamentoCompuesto() {
    }

    public MedicamentoCompuesto(MedicamentoCompuestoPK medicamentoCompuestoPK) {
        this.medicamentoCompuestoPK = medicamentoCompuestoPK;
    }

    public MedicamentoCompuesto(MedicamentoCompuestoPK medicamentoCompuestoPK, double cantidad, String unidad) {
        this.medicamentoCompuestoPK = medicamentoCompuestoPK;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public MedicamentoCompuesto(BigInteger compuestoId, String medicamentoCodigo) {
        this.medicamentoCompuestoPK = new MedicamentoCompuestoPK(compuestoId, medicamentoCodigo);
    }

    public MedicamentoCompuestoPK getMedicamentoCompuestoPK() {
        return medicamentoCompuestoPK;
    }

    public void setMedicamentoCompuestoPK(MedicamentoCompuestoPK medicamentoCompuestoPK) {
        this.medicamentoCompuestoPK = medicamentoCompuestoPK;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Compuesto getCompuesto() {
        return compuesto;
    }

    public void setCompuesto(Compuesto compuesto) {
        this.compuesto = compuesto;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentoCompuestoPK != null ? medicamentoCompuestoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentoCompuesto)) {
            return false;
        }
        MedicamentoCompuesto other = (MedicamentoCompuesto) object;
        if ((this.medicamentoCompuestoPK == null && other.medicamentoCompuestoPK != null) || (this.medicamentoCompuestoPK != null && !this.medicamentoCompuestoPK.equals(other.medicamentoCompuestoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojos.MedicamentoCompuesto[ medicamentoCompuestoPK=" + medicamentoCompuestoPK + " ]";
    }
    
}
